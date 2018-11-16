package cs.gtstudent.zwaste;

import static org.junit.Assert.*;
import org.junit.Test;

public class SHA256Tests {
    String plain;

    @Test
    public void testSHA25601EmptyString() {
        plain = "";
        assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", SHA256.getSHA256(plain));
    }

    @Test
    public void testSHA25602OneCharacterString() {
        plain = "b";
        assertEquals("3e23e8160039594a33894f6564e1b1348bbd7a0088d42c4acb73eeaed59c009d", SHA256.getSHA256(plain));
    }

    @Test
    public void testSHA25603StringNoSpaces() {
        plain = "ForgiveMeFatherForIHaveSinned";
        assertEquals("ad8cf3da38c15584d6e77ad2ea57703903d539e7deee8c866b4d021f80e0c417", SHA256.getSHA256(plain));
    }

    @Test
    public void testSHA25604StringWithSpaces() {
        plain = "Mayonnaise is indeed an instrument, gentlemen";
        assertEquals("80d324af02eed112f8f3ade0c62a55aba5cf58e15c495940ac7e7b13803a67eb", SHA256.getSHA256(plain));
    }

    @Test
    public void testSHA25605StringWithSymbols() {
        plain = "!@#$%^&*()";
        assertEquals("95ce789c5c9d18490972709838ca3a9719094bca3ac16332cfec0652b0236141", SHA256.getSHA256(plain));
    }
}
