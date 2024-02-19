package org.sergiorebelo.piupiu.usermanagement.security;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordUtilsTest {

    @Test
    void testHashPassword() {

        // Prepare
        String password = "password123";
        byte[] salt = "known_salt_for_tests".getBytes();

        // Execute
        String hashedPassword = PasswordUtils.hashPassword(password, salt);

        // Verify
        assertNotNull(hashedPassword);
        assertTrue(hashedPassword.contains(":")); // Check if the salt and hash are separated by ":"
        assertEquals(75, hashedPassword.length()); // Check if the hashed password has the correct length
        assertEquals("a25vd25fc2FsdF9mb3JfdGVzdHM===:NCKmixUUibDFXbzb2wFve+sAZV9EOtumaXg11G2yuVE=", hashedPassword);
    }

    @Test
    void testHashMethodWhenNoSuchAlgorithmExceptionIsThrown() throws Exception {
        // todo: test the case where there is an exception

    }

    @Test
    void testGenerateSalt() {
        byte[] salt = PasswordUtils.generateSalt();

        assertNotNull(salt);
        assertEquals(16, salt.length); // Check if the salt has the correct length
    }

}

