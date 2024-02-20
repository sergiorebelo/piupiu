package org.sergiorebelo.piupiu.usermanagement.api.endpoint;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.sergiorebelo.piupiu.usermanagement.business.UserService;
import org.sergiorebelo.piupiu.usermanagement.entity.User;

import org.sergiorebelo.piupiu.usermanagement.api.dto.UserDTO;
import org.sergiorebelo.piupiu.usermanagement.security.PasswordUtils;


import java.util.Optional;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    private static final Logger logger = Logger.getLogger(UserResource.class);

    @POST
    @Path("/create-user")
    public Response createUser(UserDTO user) {
        logger.trace( "Create User request for user: " + user.getUsername());
        
        // Check if the user already exists
        if (userService.isUsernameTaken(user.getUsername())) {
            // User already exists, return 409 Conflict
            return Response.status(Response.Status.CONFLICT)
                    .entity("User with that username already exists")
                    .build();
        }

        // Hash the user's password before saving it
        byte[] salt = PasswordUtils.generateSalt();
        String hashedPassword = PasswordUtils.hashPassword(user.getPassword(), salt);

        User userEntity = new User(user.getUsername(), hashedPassword, salt);

        // Proceed with the creation of the user
        userService.createUser(userEntity);

        return Response.status(Response.Status.CREATED).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") long id) {

        Optional<User> userEntity = userService.getUserById(id);
        return userEntity.map(u -> Response.ok(new UserDTO(u)).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/username/{username}")
    public Response getUserByUsername(@PathParam("username") String username) {

        Optional<User> userEntity = userService.getUserByUsername(username);
        return userEntity.map(u -> Response.ok(new UserDTO(u)).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());

    }
}
