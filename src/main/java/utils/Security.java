package utils;

import org.tinylog.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Security {

    /**
     * Sanitize input for XSS injection
     * @param input the input string
     * @return the input with special character encoded
     */
    public static String sanitizeInput(String input){
        if (input == null) {
            return null;
        }
        // Replace <, >, &, ", ' characters with their HTML entity equivalents
        input = input.replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;")
                .replaceAll("'", "&#x27;");
        return input;
    }

    /**
     * Genearate a random token
     * @return a random token
     * @throws NoSuchAlgorithmException if no Provider supports a MessageDigestSpi implementation for the specified algorithm
     */
    public static String getCSRFToken() throws NoSuchAlgorithmException {
        // generate random data
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] data = new byte[16];
        secureRandom.nextBytes(data);

        // convert to Base64 string
        return Base64.getEncoder().encodeToString(data);
    }


    /**
     * Generates a random byte array for unique hashing
     * @return a random byte array
     */
    public static byte[] generateSalt() {
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }

    /**
     * Create a hash function from a string (SHA-256)
     * @param string the input string
     * @param salt the salt that makes unique each string
     * @return the hash function
     * @throws NoSuchAlgorithmException if no Provider supports a MessageDigestSpi implementation for the specified algorithm
     */
    public static String hash(String string, byte[] salt) throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(salt);
        byte[] hashedPassword = messageDigest.digest(string.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword);

    }


}
