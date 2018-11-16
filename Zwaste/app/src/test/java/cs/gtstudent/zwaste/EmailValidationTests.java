package cs.gtstudent.zwaste;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailValidationTests {

    String email;

    @Test
    public void testValidateEmail01Valid() {
        email = "alexmcq99@yahoo.com";
        assertEquals("Valid email.", DataValidation.validateEmail(email));
    }

    @Test
    public void testValidateEmail02EmptyString() {
        email = "";
        assertEquals("Please enter an email.", DataValidation.validateEmail(email));
    }

    @Test
    public void testValidateEmail03NoAtSymbol() {
        email = "john_smithgmail.com";
        assertEquals("Your email must contain the \'@\' symbol.", DataValidation.validateEmail(email));
    }

    @Test
    public void testValidateEmail04NoPeriod() {
        email = "john_smith@gmailcom";
        assertEquals("Your email must contain the \'.\' symbol.", DataValidation.validateEmail(email));
    }

    @Test
    public void testValidateEmail05NoPeriod() {
        email = "john_smith@gmailcom";
        assertEquals("Your email must contain the \'.\' symbol.", DataValidation.validateEmail(email));
    }

    @Test
    public void testValidateEmail06MoreThanOneAdjacentAtSymbol() {
        email = "john_smith@@gmail.com";
        assertEquals("Your email must contain only one \'@\' symbol.", DataValidation.validateEmail(email));
    }

    @Test
    public void testValidateEmail07MoreThanOneAdjacentPeriod() {
        email = "john_smith@gmail..com";
        assertEquals("Your email must contain only one \'.\' symbol.", DataValidation.validateEmail(email));
    }

    @Test
    public void testValidateEmail08MoreThanOneSeparateAtSymbol() {
        email = "john@smith@gmail.com";
        assertEquals("Your email must contain only one \'@\' symbol.", DataValidation.validateEmail(email));
    }

    @Test
    public void testValidateEmail09InvalidCharacters() {
        email = "!#$%^&*()@gmail.com";
        assertEquals("Your email contains one or more invalid characters.", DataValidation.validateEmail(email));
    }

    @Test
    public void testValidateEmail10OtherInvalid() {
        email = "a@b.c";
        assertEquals("Your email is invalid; please check again.", DataValidation.validateEmail(email));
    }
}