package boundary.rest;

import domain.Tweet;
import domain.User;
import service.TweetService;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.http.HTTPBinding;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Path("users")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    private UserService us;

    @Inject
    private TweetService ts;

    public void setServices(UserService us, TweetService ts) {
        this.us = us;
        this.ts = ts;
    }
    @Context
    private HttpServletRequest httpRequest;

    @GET
    public Response getAll() {
        List<JsonObject> allUsers = new ArrayList<>();
        us.getAllUsers().forEach(user -> allUsers.add(user.toJson(httpRequest)));
        return Response.ok(allUsers).build();
    }

    @GET
    @Path("{username}/timeline")
    public Response getTimeline(@PathParam("username") String username) {
        List<JsonObject> allTweetsJson = new ArrayList<>();
        List<Tweet> allTweets = new ArrayList<>();
        allTweets = ts.getTimelineTweets(username,50);
        Collections.sort(allTweets);
        allTweets.forEach(tweet -> allTweetsJson.add(tweet.toJson(httpRequest)));
        return Response.ok(allTweetsJson).build();
    }


    @GET
    @Path("{username}")
    public Response findUser(@PathParam("username") String username) {
        return Response.ok(us.findByName(username)).build();
    }

    @PUT
    public Response addUser(User user) {
        us.addUser(user);
        return Response.ok().build();
    }

    @POST
    @Path("{username}")
    public Response editUser(User user) {
        us.editUser(user);
        return Response.ok().build();
    }

    @DELETE
    @Path("{username}")
    public Response deleteUser(@PathParam("username") String username) {
        us.removeUser(username);
        return Response.ok().build();
    }

    @GET
    @Path("{username}/followers")
    public Response getUserFollowers(@PathParam("username") String username) {
        GenericEntity<List<User>> list = new GenericEntity<List<User>>(us.getUserFollowers(username)) {};
        return Response.ok(list).build();
    }


    @GET
    @Path("{username}/following")
    public Response getUserFollowing(@PathParam("username") String username) {
        GenericEntity<List<User>> list = new GenericEntity<List<User>>(us.getUserFollowing(username)) {};
        return Response.ok(list).build();
    }

    @GET
    @Path("{username}/liked")
    public Response getUserLikedTweets(@PathParam("username") String username) {
        return Response.ok(us.findByName(username).getLikes()).build();
    }

    @GET
    @Path("{username}/tweets")
    public Response getUserTweets(@PathParam("username") String username) {
        return Response.ok(us.findByName(username).getTweets()).build();
    }

    @GET
    @Path("{username}/recenttweets")
    public Response getRecentUserTweets(@PathParam("username") String username) {
        return Response.ok(us.findByName(username).recentTweets(10)).build();
    }

    @PUT
    @Path("{username}/tweets")
    public Response postTweet(@PathParam("username") String username, Tweet tweet) {
        tweet.setPoster(us.findByName(username));
        ts.addTweet(tweet);
        return Response.ok().build();
    }

    @PUT
    @Path("{username}/followers/{newfollower}")
    public Response addFollower(@PathParam("username") String username, @PathParam("newfollower") String newFollowerName) {
        us.addFollower(username,newFollowerName);
        return Response.ok().build();
    }

    @DELETE
    @Path("{username}/followers/{oldfollower}")
    public Response removeFollower(@PathParam("username") String username, @PathParam("oldfollower") String oldFollowerName) {
        us.removeFollower(username,oldFollowerName);
        return Response.ok().build();
    }


}
