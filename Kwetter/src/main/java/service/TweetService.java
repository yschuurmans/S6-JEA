package service;

import Annotations.JPA;
import dao.TweetDAO;
import dao.UserDAO;
import domain.Hashtag;
import domain.Tweet;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
public class TweetService {

    @Inject
    @JPA
    private TweetDAO tweetDAO;

    @Inject
    private HashtagService hashtagService;

    @Inject
    private UserService userService;



    public TweetService() {

    }

    /**
     * Gets the tweets from the timeline for a certain user, defined by username
     * @param username The username of the user for which to retrieve the Timeline.
     * @param amountTweets The amount of tweets to retrieve for the Timeline.
     * @return A list of Tweets which represent the Timeline.
     */
    public List<Tweet> getTimelineTweets(String username, int amountTweets) {
        User currentUser = userService.findByName(username);
        List<Tweet> timelineTweets = new ArrayList<>();
        for (User following : currentUser.getFollowing()) {
            timelineTweets
                    .addAll(following.getTweets()
                    .subList(following.getTweets().size()-amountTweets,
                            following.getTweets().size()));
        }
        return timelineTweets.subList(timelineTweets.size() - amountTweets, timelineTweets.size());
    }


    /**
     * Posts a tweet
     * @param tweet The tweet which should be posted.
     */
    public void addTweet(Tweet tweet) {
        //Match hastag usages within tweet content, and save these to the relevant hashtag and
        Pattern p = Pattern.compile("(?<=#)\\w++");
        Matcher m = p.matcher(tweet.getTweetContent());
        List<Hashtag> usedHashtags = new ArrayList<>();

        tweetDAO.addTweet(tweet);
        while (m.find()) {
            String tagString = m.group();
            Hashtag hashtag = hashtagService.findHashtag(tagString);
            if (hashtag == null) {
                hashtag = new Hashtag(tagString);
                hashtagService.addHashtag(hashtag);
            }
            hashtag.getTweetsUsingHashtag().add(tweet);

            usedHashtags.add(hashtag);
        }

        Pattern p2 = Pattern.compile("(?<=@)\\w++");
        Matcher m2 = p.matcher(tweet.getTweetContent());
        List<User> usedMentions = new ArrayList<>();
        while (m.find()) {
            String nameString = m.group();
            User user = userService.findByName(nameString);
            if (user != null) {
                usedMentions.add(user);
            }

        }
        tweetDAO.editTweet(tweet);
        tweet.setHashTagsUsed(usedHashtags);
        tweet.setMentions(usedMentions);
        User poster = tweet.getPoster();
        poster.getTweets().add(tweet);
        userService.editUser(poster);
    }


    /**
     * Removes a tweet
     * @param tweet the tweet to remove
     */
    public void removeTweet(Tweet tweet) {
        tweetDAO.removeTweet(tweet);
    }

    /**
     * Removes a tweet, based on the ID of the tweet.
     * @param tweetID the tweet to remove.
     */
    public void removeTweet(long tweetID) {
        Tweet tweet = getTweet(tweetID);
        User poster = tweet.getPoster();
        poster.getTweets().remove(tweet);
        userService.editUser(poster);
        tweetDAO.removeTweet(tweet);
    }


    /**
     * Marks a tweet as liked by a user.
     * @param tweetid The tweet to like.
     * @param username The user which liked the tweet.
     */
    public void likeTweet(long tweetid, String username) {
        User liker = userService.findByName(username);
        Tweet likedTweet = getTweet(tweetid);
        liker.getLikes().add(likedTweet);
        likedTweet.getLikedBy().add(liker);
        userService.editUser(liker);
        tweetDAO.editTweet(likedTweet);
    }


    /**
     * Gets a tweet based on ID
     * @param id The ID of the tweet.
     * @return The tweet which corresponds with the ID.
     */
    public Tweet getTweet(long id) {
        return tweetDAO.getTweet(id);
    }


    /**
     * Searches all tweets and returns all tweets which contain the search string.
     * @param seartText The search string to match with the tweets.
     * @return All tweets which contain the search string.
     */
    public List<Tweet> searchTweets(String seartText) {
        return tweetDAO.searchTweets(seartText);
    }


    /**
     * Gets all tweets ever posted
     * @return all tweets ever posted
     */
    public List<Tweet> getAllTweets() {
        return tweetDAO.getAllTweets();
    }

    /**
     * Sets the DAO used by the service, mainly used for testing.
     * @param tweetDAO The DAO to set
     */
    public void setTweetDAO(TweetDAO tweetDAO) {
        this.tweetDAO = tweetDAO;
    }

    /**
     * Sets the Hashtag service used by the service, mainly used for testing.
     * @param hashtagService The DAO to set
     */
    public void setHashtagService(HashtagService hashtagService) {
        this.hashtagService = hashtagService;
    }

    /**
     * Sets the User service used by the service, mainly used for testing.
     * @param userService The DAO to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
