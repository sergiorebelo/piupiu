package org.sergiorebelo.piupiu.usermanagement.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserProfileTest {

    @Test
    void testConstructorAndGetters() {

        //Prepare Data
        User user = new User ("testUser", "password123");

        // Execute
        UserProfile userProfile = new UserProfile(user);

        // Verify
        assertEquals(userProfile.getUser(), user);
        assertNull(userProfile.getId());
    }


    @Test
    void tesEmptyContainer() {
        // For coverage only

        // Execute
        UserProfile userProfile = new UserProfile();

        // Verify
        assertNull(userProfile.getId());
    }

    @Test
    void testSetters() {

        // Prepare Data
        UserProfile userProfile = new UserProfile();
        User user = new User();

        // Execute
        userProfile.setUser(user);

        // Verify
        assertEquals(user, userProfile.getUser());
    }

    @Test
    void testEqualsAndHashCode() {
        //Prepare Data
        UserProfile up1 = new UserProfile(new User());
        UserProfile up2 = new UserProfile(new User());
        UserProfile up3 = new UserProfile(new User());

//        // Execute and Verify Equals
//        assertTrue(up1.equals(up2) && up2.equals(up1));
//        assertFalse(up1.equals(up3) || up3.equals(up1));
//
//        // Execute and Verify HashCode
//        assertEquals(up2.hashCode(), up2.hashCode());
//        assertNotEquals(up1.hashCode(), up3.hashCode());
        //todo: implement equals and hashcode
    }

    @Test
    void testValidationConstraints() {
        // Test validation constraints such as @Column(nullable = false, unique = true)
        // Create appropriate test cases to ensure these constraints are enforced
    }

    @Test
    void testSerialization() {
        // Test serialization and deserialization of User objects
        // Serialize a User object, then deserialize it and ensure it's equal to the original object
    }
}
