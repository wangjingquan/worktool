import com.suning.epps.merchantsignature.common.RSAUtil;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

/**
 * @author jingquanwang
 */
public class PublicAndPrivateKeyGenerator {

  public static void main(String[] args) {

    try {
      Map<String, Object> keyMap = RSAUtil.create1024Key();
      PublicKey publicKeyBase64 = RSAUtil.getPublicKey(keyMap.get("publicKeyBase64").toString());
      PrivateKey privateKeyBase64 = RSAUtil
          .getPrivateKey(keyMap.get("privateKeyBase64").toString());
      System.out.println("公钥：" + RSAUtil.getBase64PublicKeyString(publicKeyBase64));
      System.out.println("私钥：" + RSAUtil.getBase64PrivateKeyString(privateKeyBase64));
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
