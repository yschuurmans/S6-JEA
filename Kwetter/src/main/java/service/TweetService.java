package service;

import dao.TweetDAO;
import dao.UserDAO;
import domain.Hashtag;
import domain.Tweet;
import domain.User;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetService {

    @Inject
    private TweetDAO tweetDAO;

    @Inject
    private HashtagService hashtagService;

    @Inject
    private UserService userService;



    public TweetService() {

    }

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

    public void addTweet(Tweet tweet) {
        //Match hastag usages within tweet content, and save these to the relevant hashtag and
        Pattern p = Pattern.compile("(?<=#)\\w++");
        Matcher m = p.matcher(tweet.getTweetContent());
        List<Hashtag> usedHashtags = new ArrayList<>();
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

        tweet.setHashTagsUsed(usedHashtags);
        tweet.setMentions(usedMentions);
        tweetDAO.addTweet(tweet);
        User poster = tweet.getPoster();
        poster.getTweets().add(tweet);
        userService.editUser(poster);
    }

    public void removeTweet(Tweet tweet) {
        tweetDAO.removeTweet(tweet);
    }

    public void likeTweet(long tweetid, String username) {
        User liker = userService.findByName(username);
        Tweet likedTweet = getTweet(tweetid);
        liker.getLikes().add(likedTweet);
        likedTweet.getLikedBy().add(liker);
        userService.editUser(liker);
        tweetDAO.editTweet(likedTweet);
    }

    public Tweet getTweet(long id) {
        return tweetDAO.getTweet(id);
    }

    public List<Tweet> searchTweets(String seartText) {
        return tweetDAO.searchTweets(seartText);
    }

    public List<Tweet> getAllTweets() {
        return tweetDAO.getAllTweets();
    }

    public void setTweetDAO(TweetDAO tweetDAO) {
        this.tweetDAO = tweetDAO;
    }

    public void setHashtagService(HashtagService hashtagService) {
        this.hashtagService = hashtagService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
