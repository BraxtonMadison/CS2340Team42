package cs.gtstudent.zwaste;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {
    public static String validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern invalidPattern = Pattern.compile("[^a-zA-Z0-9!#$%&'*+\\-/=?^_`{|}~\\.@]");
        Matcher invalidMatcher = invalidPattern.matcher(email);

        int numAts = 0;
        int numPeriods = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                numAts++;
            } else if (email.charAt(i) == '.') {
                numPeriods++;
            }
        }

        if (email.equals("")) {
            return "Please enter an email.";
        } else if (email.matches(emailRegex)) {
            return "Valid email.";
        } else if (numAts == 0) {
            return "Your email must contain the \'@\' symbol.";
        } else if (numAts > 1) {
            return "Your email must contain only one \'@\' symbol.";
        } else if (numPeriods == 0) {
            return "Your email must contain the \'.\' symbol.";
        } else if (numPeriods > 1) {
            return "Your email must contain only one \'.\' symbol.";
        } else if (invalidMatcher.find()) {
            return "Your email contains one or more invalid characters.";
        } else {
            return "Your email is invalid; please check again.";
        }
    }

    public static String validatePassword (String password) {

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

        return "Valid password.";
    }
}
