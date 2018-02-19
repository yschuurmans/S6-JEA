package boundary.rest;

import domain.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("ping")
public class PingResource {

    @GET
    public String ping() {
        List<User> users = new ArrayList<>();
        users.add(new User("Youri", "Oss", "YourisPassword"));
        users.add(new User("Ken", "KutDorp", "KensPassword"));
        users.add(new User("Mike", "NogeenKutdorp", "MikesPassword"));

        User temp = new User();
        String searchTag = "";

        for (User u : users) {
            if(u.getUsername().contains(searchTag))
                temp = u;
        }
        temp.setBio("WTFWIEWILDAARWONEN");

        for (User u : users) {

        }

        return "abc";

    }

}
