package cs.gtstudent.zwaste;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordValidationTests {
    String pw;

    @Test
    public void testValidatePassword01Valid() {
        pw = "#GeorgiaTechIsTheBE5T!";
        assertEquals("Valid password.", DataValidation.validatePassword(pw));
    }

    @Test
    public void testValidatePassword02EmptyString() {
        pw = "";
        assertEquals("Password must be at least 8 characters long.", DataValidation.validatePassword(pw));
    }

    @Test
    public void testValidatePassword03TooShort() {
        pw = "#Hey1";
        assertEquals("Password must be at least 8 characters long.", DataValidation.validatePassword(pw));
    }

    @Test
    public void testValidatePassword04NoSpecialCharacters() {
        pw = "Thi5i5mypa55wordokay";
        assertEquals("Password must contain at least one special character.", DataValidation.validatePassword(pw));
    }

    @Test
    public void testValidatePassword05NoNumbers() {
        pw = "Thisismypasswordokay?";
        assertEquals("Password must contain at least one number.", DataValidation.validatePassword(pw));
    }

    @Test
    public void testValidatePassword06NoCapitalLetters() {
        pw = "thi5i5mypa55wordokay?";
        assertEquals("Password must contain at least one capital letter.", DataValidation.validatePassword(pw));
    }
}
