package org.sergiorebelo.piupiu.usermanagement.business;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sergiorebelo.piupiu.usermanagement.business.UserProfileService;
import org.sergiorebelo.piupiu.usermanagement.entity.User;
import org.sergiorebelo.piupiu.usermanagement.persistence.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserProfileService userProfileService;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers_Empty() {
        List<User> userList = new ArrayList<>();
        //userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAllUsers();

        assertEquals(0, result.size());
    }

    @Test
    void testGetAllUsers_One() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAllUsers();

        assertEquals(1, result.size());
    }

    @Test
    void testGetAllUsers_More() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAllUsers();

        assertEquals(3, result.size());
    }

    @Test
    void testCreateUser() {
        User user = new User();
        userService.createUser(user);

        verify(userRepository, times(1)).createUser(user);
        verify(userProfileService, times(1)).createUserProfile(user);
    }

    @Test
    void testGetUserById() {
        long userId = 1;
        User user = new User();
        when(userRepository.getUserById(userId)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(userId);

        assertTrue(result.isPresent());
        assertEquals(user, result.orElse(null));
    }


    @Test
    void testGetUserById_NotFound() {
        long userId = 1;

        when(userRepository.getUserById(userId)).thenReturn(Optional.ofNullable(null));

        Optional<User> result = userService.getUserById(userId);

        assertTrue(result.isEmpty());

    }

    @Test
    void testGetUserByUsername() {
        String username = "testUser";
        User user = new User();
        when(userRepository.getUserByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByUsername(username);

        assertTrue(result.isPresent());
        assertEquals(user, result.orElse(null));
    }



    @Test
    void testGetUserByUsername_NotFound() {
        String username = "testUser";

        when(userRepository.getUserByUsername(username)).thenReturn(Optional.ofNullable(null));

        Optional<User> result = userService.getUserByUsername(username);

        assertTrue(result.isEmpty());
    }
}
