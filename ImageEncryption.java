import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageEncryption extends Component {

    ImageEncryption() throws Exception{
        new AESKeyGenerator(); //Made a call to generate Key

        // Read the input image file into a byte array
        String location = Encrypt_Window.FilePATH;
        File imageFile = new File(location);
        FileInputStream fis = new FileInputStream(imageFile);
        byte[] imageBytes = new byte[(int) imageFile.length()];
        fis.read(imageBytes);
        fis.close();

        // Convert the hex key to binary
        String keyHex = AESKeyGenerator.Key;
        KeySplit.Secret_Sharing(keyHex);
        byte[] key = hexStringToByteArray(keyHex);

        // Pad the image data to match the block size of AES (16 bytes)
        int blockSize = 16;
        int paddedLength = ((imageBytes.length - 1) / blockSize + 1) * blockSize;
        byte[] paddedImageBytes = new byte[paddedLength];
        System.arraycopy(imageBytes, 0, paddedImageBytes, 0, imageBytes.length);

        // Generate a random initialization vector (IV)
        byte[] iv = new byte[blockSize];
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Create the AES cipher object with the key and IV
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // Encrypt the padded image data using AES CBC mode
        byte[] encryptedImageBytes = cipher.doFinal(paddedImageBytes);

        // Write the encrypted image data to a new file
        FileOutputStream fos = new FileOutputStream(Encrypt_Window.ParentDirectory+"/"+ Encrypt_Window.FileName+".jpg");
        fos.write(iv);
        fos.write(encryptedImageBytes);
        JOptionPane.showMessageDialog(null,"Encryption Successful");
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
