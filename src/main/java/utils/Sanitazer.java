package utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Sanitazer {
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


    public static String getCSRFToken() throws NoSuchAlgorithmException {
        // generate random data
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] data = new byte[16];
        secureRandom.nextBytes(data);

        // convert to Base64 string
        return Base64.getEncoder().encodeToString(data);
    }
}
