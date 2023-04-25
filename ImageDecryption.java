import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageDecryption extends Component {
    ImageDecryption() throws Exception{
        // Read the input encrypted image file into a byte array
        String location = Decrypt_Window.FilePATH;
        File encryptedImageFile = new File(location);
        FileInputStream fis = new FileInputStream(encryptedImageFile);
        byte[] encryptedImageBytes = new byte[(int) encryptedImageFile.length()];
        fis.read(encryptedImageBytes);
        fis.close();

        // Convert the hex key to binary
        String keyHex = KeySplit.Decode_Secret();
        byte[] key = hexStringToByteArray(keyHex);

        // Extract the initialization vector (IV) from the encrypted image data
        int blockSize = 16;
        byte[] iv = new byte[blockSize];
        System.arraycopy(encryptedImageBytes, 0, iv, 0, blockSize);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Create the AES cipher object with the key and IV
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // Decrypt the encrypted image data using AES CBC mode
        int encryptedDataOffset = blockSize;
        int encryptedDataLength = encryptedImageBytes.length - blockSize;
        byte[] decryptedImageBytes = cipher.doFinal(encryptedImageBytes, encryptedDataOffset, encryptedDataLength);

        // Remove any padding bytes from the decrypted image data
        int originalDataLength = decryptedImageBytes.length;
        for (int i = originalDataLength - 1; i >= 0; i--) {
            if (decryptedImageBytes[i] != 0) {
                break;
            }
            originalDataLength--;
        }

        // Write the decrypted image data to a new file
        FileOutputStream fos = new FileOutputStream(Decrypt_Window.ParentDirectory+"/"+ Decrypt_Window.FileName+".jpg");
        fos.write(decryptedImageBytes, 0, originalDataLength);
        JOptionPane.showMessageDialog(null,"Decryption Successful");
        fos.close();
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
