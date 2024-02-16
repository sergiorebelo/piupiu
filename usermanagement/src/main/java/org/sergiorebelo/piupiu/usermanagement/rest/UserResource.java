package org.sergiorebelo.piupiu.usermanagement.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.sergiorebelo.piupiu.usermanagement.business.UserService;
import org.sergiorebelo.piupiu.usermanagement.entity.User;
import org.sergiorebelo.piupiu.usermanagement.security.PasswordUtils;

import java.util.Optional;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @POST
    public Response createUser(User user) {

        // Hash the user's password before saving it
        byte[] salt = PasswordUtils.generateSalt();
        String hashedPassword = PasswordUtils.hashPassword(user.getHashedPassword(), salt);
        user.setHashedPassword(hashedPassword);

        // Proceed with the creation of the user
        userService.createUser(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") long id) {

        Optional<User> user = userService.getUserById(id);
        return user.map(u -> Response.ok(u).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/username/{username}")
    public Response getUserByUsername(@PathParam("username") String username) {

        Optional<User> user = userService.getUserByUsername(username);
        return user.map(u -> Response.ok(u).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
