package utils;

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
}
