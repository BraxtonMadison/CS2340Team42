package cs.gtstudent.zwaste;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogInFragmentTest {

    String email;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIsValidEmail01BasicValid() {
        email = "alexmcq99@yahoo.com";
        assertEquals(true, LogInFragment.isValidEmail(email));
    }

    @Test
    public void testIsValidEmail02EmptyStringInvalid() {
        email = "";
        assertEquals(false, LogInFragment.isValidEmail(email));
    }
}