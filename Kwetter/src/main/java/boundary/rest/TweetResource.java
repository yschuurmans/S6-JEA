package boundary.rest;

import Annotations.TokenRequired;
import domain.Role;
import domain.Tweet;
import domain.User;
import service.TweetService;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("tweets")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@TokenRequired(requiredPermissionGroup = Role.User)
public class TweetResource {
    @Inject
    private TweetService ts;

    @GET
    public Response getAll() {
        return Response.ok(ts.getAllTweets()).build();
    }

    @GET
    @Path("search")
    public Response searchTweets(@QueryParam("searchString") String searchString) {
        return Response.ok(ts.searchTweets(searchString)).build();
    }

    @GET
    @Path("{id}")
    public Response getTweet(@PathParam("id") long id) {
        return Response.ok(ts.getTweet(id)).build();
    }

}
