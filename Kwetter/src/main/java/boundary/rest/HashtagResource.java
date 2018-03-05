package boundary.rest;

import domain.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("hashtags")
@Stateless
public class HashtagResource {
    @Inject
    private service.HashtagService s;

    @GET
    public Response getAll() {
        return Response.ok(s.getAllHashtags()).build();
    }

    @GET
    @Path("{hashtag}")
    public Response findHashtag(@PathParam("hashtag") String hashtag) {
        return Response.ok(s.findHashtag(hashtag)).build();
    }

    @GET
    @Path("search/{hashtag}")
    public Response searchHashtags(@PathParam("hashtag") String hashtag) {
        return Response.ok(s.searchHashtags(hashtag)).build();
    }

}
