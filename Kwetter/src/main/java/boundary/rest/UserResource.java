package boundary.rest;

import domain.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("users")
@Stateless
public class UserResource {
    @Inject
    private UserService s;

    @GET
    public List<User> getAll() {
        return s.getAllUsers();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findUser(@PathParam("id") int id) {
        return s.getUser(id);
    }

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findUser(@PathParam("username") String username) {
        return s.findByName(username);
    }

    @PUT
    @Path("{username}")
    public String addUser(@PathParam("username") String username, @QueryParam("bio") String bio, @QueryParam("password") String password) {
        s.addUser(new User(username, bio, password));
        return "";
    }

    @POST
    @Path("{username}")
    public String editUser(@PathParam("username") String username, @QueryParam("bio") String bio, @QueryParam("password") String password) {
        s.addUser(new User(username, bio, password));
        return "";
    }

    @DELETE
    @Path("{username}")
    public String deleteUser(@PathParam("username") String username) {
        s.removeUser(username);
        return "";
    }

    @GET
    @Path("{username}/followers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUserFollowers(@PathParam("username") String username) {
        return s.getUserFollowers(username);
    }

    @GET
    @Path("{id}/followers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUserFollowers(@PathParam("id") int id) {
        return s.getUserFollowers(id);
    }

    @GET
    @Path("{username}/following")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUserFollowing(@PathParam("username") String username) {
        return s.getUserFollowing(username);
    }

    @GET
    @Path("{id}/following")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUserFollowing(@PathParam("id") int id) {
        return s.getUserFollowing(id);
    }

    @PUT
    @Path("{id}/following")
    @Produces(MediaType.APPLICATION_JSON)
    public void addFollower(@PathParam("id") int toFollow, @QueryParam("idFollower") int follower) {
        s.addFollower(toFollow, follower);
    }

    @DELETE
    @Path("{id}/following")
    @Produces(MediaType.APPLICATION_JSON)
    public void removeFollower(@PathParam("id") int toUnfollow, @QueryParam("idFollower") int follower) {
        s.removeFollower(toUnfollow, follower);
    }

}
