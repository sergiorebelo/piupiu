package org.sergiorebelo.piupiu;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.sergiorebelo.piupiu.usermanagement.business.UserService;
import org.sergiorebelo.piupiu.usermanagement.entity.User;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Path("/hello")
public class GreetingResource {

    @Inject
    UserService userService;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {


        List<User> userList = userService.getAllUsers();


        return "Hello " + userList.stream()
                .map(User::getUsername)
                .collect(Collectors.joining(" ,")) + ".";
    }


}
