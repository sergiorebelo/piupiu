package org.sergiorebelo.piupiu;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.sergiorebelo.piupiu.usermanagement.business.UserService;
import org.sergiorebelo.piupiu.usermanagement.entity.User;

import java.util.List;

@Path("/hello")
public class GreetingResource {

    @Inject
    UserService userService;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        String helloStr = "Hello ";
        // Call the getAllUsers() method
        List<User> userList = userService.getAllUsers();

        // Do something with the list of users
        for (User user : userList) {
            helloStr += user.getUsername();
        }


        return helloStr;
    }


}
