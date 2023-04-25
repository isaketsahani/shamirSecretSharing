import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AESKeyGenerator {
    protected static String Key;
    AESKeyGenerator() throws Exception {
        SecretKey secretKey = generateKey();
        String SecretKeyData = bytesToHex(secretKey.getEncoded());
        //System.out.println("Generated AES Key: " + SecretKeyData);
        Key = SecretKeyData;

    }

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // AES key length is 128 bits
        return keyGenerator.generateKey();
    }

    // Utility method to convert byte array to hexadecimal string
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_ARRAY[v >>> 4];
            hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

}
