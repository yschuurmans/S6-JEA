package boundary.rest;

import domain.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
@Stateless
public class UserResource {
    @Inject
    private UserService s;

    @GET
    public Response getAll() {
        return Response.ok(s.getAllUsers()).build();
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
    @Path("{username}/following")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUserFollowing(@PathParam("username") String username) {
        return s.getUserFollowing(username);
    }


}
