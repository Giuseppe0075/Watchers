package utils;

import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
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

    public static boolean checkCSRFToken(HttpSession session, String token){
        if(session == null || token == null){
            return false;
        }
        return session.getAttribute("CSRF-Token").equals(token);
    }

    public static byte[] hashPassword(String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        SecretKeyFactory factory = null;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
        byte[] hash = null;
        byte[] combined; // Array to store the combined salt and hash

        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = factory.generateSecret(spec).getEncoded();

            // Concatenate salt and hash
            combined = new byte[salt.length + hash.length];
            System.arraycopy(salt, 0, combined, 0, salt.length);
            System.arraycopy(hash, 0, combined, salt.length, hash.length);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

        return combined;
    }

    public static boolean verifyPassword(String enteredPassword, byte[] combinedHash) {
        if (combinedHash == null || combinedHash.length == 0) {
            throw new IllegalArgumentException("Combined hash cannot be null or empty");
        }

        // Separate the salt from the combined hash
        byte[] salt = new byte[16];
        System.arraycopy(combinedHash, 0, salt, 0, salt.length);

        // Extract the hash from the combined hash
        byte[] storedHash = new byte[combinedHash.length - salt.length];
        System.arraycopy(combinedHash, salt.length, storedHash, 0, storedHash.length);

        // Generate a new hash using the entered password and the extracted salt
        KeySpec spec = new PBEKeySpec(enteredPassword.toCharArray(), salt, 65536, 256);
        SecretKeyFactory factory = null;
        byte[] newHash = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            newHash = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

        // Compare the newly generated hash with the stored hash
        return Arrays.equals(newHash, storedHash);
    }

}