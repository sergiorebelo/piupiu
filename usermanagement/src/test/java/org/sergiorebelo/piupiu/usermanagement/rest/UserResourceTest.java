package org.sergiorebelo.piupiu.usermanagement.rest;


import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sergiorebelo.piupiu.usermanagement.api.dto.UserDTO;
import org.sergiorebelo.piupiu.usermanagement.api.endpoint.UserResource;
import org.sergiorebelo.piupiu.usermanagement.business.UserService;
import org.sergiorebelo.piupiu.usermanagement.entity.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserResourceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserResource userResource;

    @Test
    void testCreateUser() {

        // Prepare
        User user = new User("testUser", "password123");
        doNothing().when(userService).createUser(user);

        // Execute
        Response response = userResource.createUser(new UserDTO(user));

        // Verify
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        verify(userService, times(1)).createUser(user);
    }

    @Test
    void testCreateUser_alreadyExists() {

        // Prepare
        UserDTO user = new UserDTO("testUser", "password123");
        when(userService.isUsernameTaken(user.getUsername())).thenReturn(true);

        // Execute
        Response response = userResource.createUser(user);

        // Verify
        assertEquals(Response.Status.CONFLICT.getStatusCode(), response.getStatus());
        verify(userService, times(0)).createUser(new User());
    }

    @Test
    void testGetUserById_UserExists() {

        // Prepare
        long userId = 1L;
        User user = new User("testUser", "password123");
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));

        // Execute
        Response response = userResource.getUserById(userId);

        // Verify
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        UserDTO created = (UserDTO)response.getEntity();
        assertEquals(user.getUsername(), created.getUsername() );
    }

    @Test
    void testGetUserById_UserNotExists() {

        // Prepare
        long userId = 1L;
        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        // Execute
        Response response = userResource.getUserById(userId);

        // Verify
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    void testGetUserByUsername_UserExists() {

        // Prepare
        String username = "testUser";
        User user = new User(username, "password123");
        when(userService.getUserByUsername(username)).thenReturn(Optional.of(user));

        // Execute
        Response response = userResource.getUserByUsername(username);

        // Verify
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        UserDTO created = (UserDTO)response.getEntity();
        assertEquals(user.getUsername(), created.getUsername() );
    }

    @Test
    void testGetUserByUsername_UserNotExists() {

        // Prepare
        String username = "testUser";
        when(userService.getUserByUsername(username)).thenReturn(Optional.empty());

        // Execute
        Response response = userResource.getUserByUsername(username);

        // Verify
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
}
