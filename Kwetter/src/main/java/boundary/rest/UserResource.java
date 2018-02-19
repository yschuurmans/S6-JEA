package boundary.rest;

import domain.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("users")
@Stateless
public class UserResource {
    @Inject
    private UserService s;

    @GET
    public List<User> getAll() { return s.getAllUsers();}

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public User get(@PathParam("username") String username) { return s.findByName(username);}
}
