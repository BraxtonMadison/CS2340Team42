package cs.gtstudent.zwaste;

import static org.junit.Assert.*;
import org.junit.Test;

public class ValidateRegistrationInfoTests {
    String email;
    String pw;

    @Test
    public void testCheckRegisterInfo01ValidInfo() {
        email = "coolkid@genericwebsite.com";
        pw = "IHav3AGr8Pa55Word!";
        assertEquals("Valid info.", DataValidation.validateRegistrationInfo(email, pw));
    }

    @Test
    public void testCheckRegisterInfo02InvalidEmail() {
        email = "coolestkidaround@waitAMinuteThisIsNotAValidEmail";
        pw = "IHav3AGr8Pa55Word!";
        assertEquals("Your email must contain the \'.\' symbol.", DataValidation.validateRegistrationInfo(email, pw));
    }

    @Test
    public void testCheckRegisterInfo03InvalidPassword() {
        email = "coolestkidaround@waitAMinuteThisIsAValidEmail.net";
        pw = "IDONOTHav3AGr8Pa55Word";
        assertEquals("Password must contain at least one special character.", DataValidation.validateRegistrationInfo(email, pw));
    }
}
