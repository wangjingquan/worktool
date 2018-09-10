import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;

/**
 * @author jingquanwang
 * @date 2018/3/26
 */
public class PassportUtil {

/*  private static final String des3KeyHexIv = "70706C6976656F6B";
  private static final String des3Key02 = "29028A7698EF4C6D3D252F02F4F79D5815389DF18525D326";
  private static final String des3Key04 = "435229C8F79831131923F18C5DE32F253E2AF2AD348C4615";*/

  private static final long DEFAULT_TOKEN_EXPIRE_TIME = 14 * 24 * 3600 * 1000;

  public static String generateToken(String user) {
    RsaPrivateKeyEncrypter encrypter = new RsaPrivateKeyEncrypter();

    long expireTime = new DateTime().getMillis() + DEFAULT_TOKEN_EXPIRE_TIME;
    String token = encrypter.encrypt(user + "&" + expireTime + "&" + RandomStringUtils.randomAlphanumeric(8));
    try {
      token = URLEncoder.encode(token, "utf-8");
    } catch (UnsupportedEncodingException e) {
    }

    return token;
  }
}
