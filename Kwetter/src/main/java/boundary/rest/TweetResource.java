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

@Path("tweets")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
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

}
