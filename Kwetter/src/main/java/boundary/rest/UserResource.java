package boundary.rest;

import domain.Tweet;
import domain.User;
import service.TweetService;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    private UserService us;

    @Inject
    private TweetService ts;

    @GET
    public Response getAll() {
        return Response.ok(us.getAllUsers()).build();
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
    public Response editUser(@PathParam("username") String username, @QueryParam("bio") String bio, @QueryParam("password") String password) {
        us.addUser(new User(username, bio, password));
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
        return Response.ok(us.getUserFollowers(username)).build();
    }


    @GET
    @Path("{username}/following")
    public Response getUserFollowing(@PathParam("username") String username) {
        return Response.ok(us.getUserFollowing(username)).build();
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
