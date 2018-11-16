package cs.gtstudent.zwaste;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
class DataValidation {
    /**
     *
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (email.matches(emailRegex)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param password
     * @return
     */
    public static String isValidPassword (String password) {

        if (password.length() < 8) {
            return "Password must be at least 8 characters long.";
        }

        String regex = "[!@#$%^&*(),.?\":{}|<>]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.find()) {
            return "Password must contain at least one special character.";
        }

        regex = "[0-9]";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(password);
        if (!matcher.find()) {
            return "Password must contain at least one number.";
        }

        regex = "(.*[A-Z].*)";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(password);
        if (!matcher.find()) {
            return "Password must contain at least one capital letter.";
        }

        return "Valid Password.";
    }
}
