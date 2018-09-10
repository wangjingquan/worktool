import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * @author jingquanwang
 * @date 2018/3/26
 */
public class RsaPrivateKeyEncrypter {

  private Cipher ecipher;
  private Cipher dcipher;

  private static final String PUBLIC_KEY_FILE = "publicKey";
  private static final String PRIVATE_KEY_FILE = "privateKey";

  private static final RsaPrivateKeyEncrypter instance = new RsaPrivateKeyEncrypter();

  public static RsaPrivateKeyEncrypter getInstance() {
    return instance;
  }

  public RsaPrivateKeyEncrypter() {
    try {
      ClassLoader cl = Thread.currentThread().getContextClassLoader();
      InputStream inStream = cl.getResourceAsStream(PUBLIC_KEY_FILE);
      ObjectInputStream publicInput = new ObjectInputStream(inStream);
      PublicKey publicKey = (PublicKey) publicInput.readObject();

      inStream = cl.getResourceAsStream(PRIVATE_KEY_FILE);
      ObjectInputStream privateInput = new ObjectInputStream(inStream);
      PrivateKey privateKey = (PrivateKey) privateInput.readObject();

      ecipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      dcipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      ecipher.init(Cipher.ENCRYPT_MODE, privateKey);
      dcipher.init(Cipher.DECRYPT_MODE, publicKey);
    } catch (IOException e) {
    } catch (ClassNotFoundException e) {
    } catch (NoSuchAlgorithmException e) {
    } catch (NoSuchPaddingException e) {
    } catch (InvalidKeyException e) {
    }
  }

  public String encrypt(String str) {
    try {
      // Encode the string into bytes using utf-8
      byte[] utf8 = str.getBytes("UTF8");
      // Encrypt
      byte[] enc = ecipher.doFinal(utf8);
      // Encode bytes to base64 to get a string
      // return new sun.misc.BASE64Encoder().encode(enc);
      return new String(new org.apache.commons.codec.binary.Base64(true).encode(enc));
    } catch (javax.crypto.BadPaddingException e) {
    } catch (IllegalBlockSizeException e) {
    } catch (UnsupportedEncodingException e) {
    }
    return null;
  }

  public String decrypt(String str) {
    try {
      // Decode base64 to get bytes
      // byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
      byte[] dec = new org.apache.commons.codec.binary.Base64(true).decode(str);
      // Decrypt
      byte[] utf8 = dcipher.doFinal(dec);
      // Decode using utf-8
      return new String(utf8, "UTF8");
    } catch (javax.crypto.BadPaddingException e) {
    } catch (IllegalBlockSizeException e) {
    } catch (UnsupportedEncodingException e) {
    }
    return null;
  }
}
