package cs.gtstudent.zwaste;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Static Model class for SHA hashing of received String value.
 */
class SHA256 {
    /**
     * Static method for hashing received String value with SHA256 algorithm.
     * @param plain String value yet to be hashed.
     * @return Hashed string value.
     */
    public static String getSHA256(final String plain) {
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = msgDigest.digest(
                    plain.getBytes(StandardCharsets.UTF_8)
            );
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : encodedHash) {
                stringBuilder.append(String.format("%02x", b));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
