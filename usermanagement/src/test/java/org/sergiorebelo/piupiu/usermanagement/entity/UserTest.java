package org.sergiorebelo.piupiu.usermanagement.entity;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.security.Security;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testConstructorAndGetters() {
        User user = new User("testUser", "password123");

        assertEquals("testUser", user.getUsername());
        // todo: we cannot have password without salt
        //  assertEquals("password123", user.getHashedPassword());
    }

    @Test
    void testSetters() {
        User user = new User();
        user.setUsername("testUser");
        user.setHashedPassword("password123");
        // todo: salt

        assertEquals("testUser", user.getUsername());
        assertEquals("password123", user.getHashedPassword());
    }

    @Test
    void testEqualsAndHashCode() {
        User user1 = new User("user1", "password1");
        User user2 = new User("user1", "password1");
        User user3 = new User("user2", "password2");

        assertTrue(user1.equals(user2) && user2.equals(user1));
        assertFalse(user1.equals(user3) || user3.equals(user1));

        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }

    @Test
    void testGetSalt() {

        // Prepare
        User user = new User();

        // Execute and Verify (empty salt)
        assertNull(user.getSalt());

        // Prepare (present salt)
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16]; // Example size, adjust as needed
        random.nextBytes(salt);
        user.setSalt(salt);

        // Execute and Verify
        assertNotNull(user.getSalt());
    }

    @Test
    void testValidationConstraints() {
        // Test validation constraints such as @Column(nullable = false, unique = true)
        // Create appropriate test cases to ensure these constraints are enforced
        // todo
    }

    @Test
    void testSerialization() {
        // Test serialization and deserialization of User objects
        // Serialize a User object, then deserialize it and ensure it's equal to the original object
        // todo
    }


}
