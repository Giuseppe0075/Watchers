package utils;

import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

public class Security {

    public static final List<String> allowedImageExt = List.of("jpg", "jpeg", "png");
    public static final long maxFileLenght = 10 * 1024 * 1024; // 10 MB
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
     */
    public static String getCSRFToken(){
        // generate random data
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
            byte[] data = new byte[16];
            secureRandom.nextBytes(data);

            // convert to Base64 string
            return Base64.getEncoder().encodeToString(data);
        }catch (Exception e){
            Logger.error("Failed to generate CSRF TOKEN", e);
            return null;
        }
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


    public static boolean checkCSRFToken(HttpSession session, String token){
        if(session == null || token == null){
            return false;
        }
        return session.getAttribute("CSRF-Token").equals(token);
    }

    /**
     * Check if the password has:
     * - at least 1 lower case (?=.*[a-z])
     * - at least 1 upper case (?=.*[A-Z])
     * - at least 1 digit (?=.*\\d)
     * - at least 1 special symbol (?=.*[@#$%^&+=])
     * - minimum 8 characters long, max 20 chars long .{8,20}
     * @return true if the requirements are met
     */
    public static boolean checkPasswordForm(String password){
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@#$%^&+=]).{8,}$");

    }




}
