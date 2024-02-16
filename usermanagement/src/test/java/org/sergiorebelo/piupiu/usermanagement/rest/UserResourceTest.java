package org.sergiorebelo.piupiu.usermanagement.rest;


import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sergiorebelo.piupiu.usermanagement.business.UserService;
import org.sergiorebelo.piupiu.usermanagement.entity.User;
import org.sergiorebelo.piupiu.usermanagement.security.PasswordUtils;

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
        User user = new User("testUser", "password123");
        doNothing().when(userService).createUser(user);

        String hashedPassword = "hashedPassword123";


        Response response = userResource.createUser(user);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        verify(userService, times(1)).createUser(user);
    }

    @Test
    void testGetUserById_UserExists() {
        long userId = 1L;
        User user = new User("testUser", "password123");
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));

        Response response = userResource.getUserById(userId);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(user, response.getEntity());
    }

    @Test
    void testGetUserById_UserNotExists() {
        long userId = 1L;
        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        Response response = userResource.getUserById(userId);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    void testGetUserByUsername_UserExists() {
        String username = "testUser";
        User user = new User(username, "password123");
        when(userService.getUserByUsername(username)).thenReturn(Optional.of(user));

        Response response = userResource.getUserByUsername(username);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(user, response.getEntity());
    }

    @Test
    void testGetUserByUsername_UserNotExists() {
        String username = "testUser";
        when(userService.getUserByUsername(username)).thenReturn(Optional.empty());

        Response response = userResource.getUserByUsername(username);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
}
