package org.sergiorebelo.piupiu.usermanagement.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordUtils {

    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    public static String hashPassword(String password, byte[] salt) {

        byte[] hashedPassword = hash(password.toCharArray(), salt);
        return Base64.getEncoder().encodeToString(salt) + "==:" + Base64.getEncoder().encodeToString(hashedPassword);
    }

    public static byte[] generateSalt() {
        // Generate a random salt
        try {
            return SecureRandom.getInstanceStrong().generateSeed(16);
        } catch (NoSuchAlgorithmException e) {
            //todo: log error;
            throw new RuntimeException(e);
        }
    }

    private static byte[] hash(char[] password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }


}
