package cs.gtstudent.zwaste;

import android.text.style.TabStopSpan;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    public static String getSHA256(final String plain) {
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = msgDigest.digest(
                    plain.getBytes(StandardCharsets.UTF_8)
            );
            StringBuilder strb = new StringBuilder();
            for (byte b : encodedHash) {
                strb.append(String.format("%02x", b));
            }
            return strb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
