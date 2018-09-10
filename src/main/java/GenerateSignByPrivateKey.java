import com.alibaba.fastjson.JSON;
import com.suning.epps.merchantsignature.common.RSAUtil;
import fileutil.BufferedInputFile;
import fileutil.ReadFile;
import fileutil.WriteFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.filechooser.FileSystemView;
import org.apache.commons.net.ntp.TimeStamp;
import org.joda.time.DateTime;

/**
 * @author jingquanwang
 */
public class GenerateSignByPrivateKey {

  public static void main(String[] args) throws IOException {
    Map<String, Object> parameters = new TreeMap<String, Object>(new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        // 升序排序
        return o1.compareTo(o2);
      }
    });

    File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
    //BufferedInputFile性能较好
    BufferedInputFile bufferedInputFile = new BufferedInputFile();
    String str = bufferedInputFile.read(homeDirectory.getAbsolutePath() + "\\parameterJson.txt");
    System.out.println(str);
    String priKey = "";
    Map<String, Object> unSortParameters = (Map<String, Object>) JSON.parse(str.toString());
    for (String key : unSortParameters.keySet()) {
      if ("priKey".equals(key)) {
         priKey = unSortParameters.get(key).toString();
      } else {
        parameters.put(key, unSortParameters.get(key));
      }
    }

    StringBuilder paramStingBuilder = new StringBuilder();
    for (String key : parameters.keySet()) {
      paramStingBuilder.append(key).append("=").append(parameters.get(key)).append("&");
    }
    paramStingBuilder.deleteCharAt(paramStingBuilder.length() - 1);
    System.out.println("parameterStr------>" + paramStingBuilder.toString());
    String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQClEUcy5cTMLGf4dEWxFiGOIovHa3id45wyWdUZ\n"
        + "lEpRgrQUkt2Sb0PyUAjveDOdHxd7WLGSXaeGITEAiMPYDVw4fIdz31MnXLt+qxfu5oTEZMDa9Fk5\n"
        + "9oLiuuViKG4Fb+2yWhJtnA6AhTkjAMYWpRihFFPGyWnIO9C9feX5r4oCmwIDAQAB";

    String sign = "";
    boolean flag = false;
    try {
      sign = RSAUtil.sign(paramStingBuilder.toString(), RSAUtil.getPrivateKey(priKey));
      flag = RSAUtil.vertiy(paramStingBuilder.toString(), sign, RSAUtil.getPublicKey(publicKey));
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("私钥签名sign--------------->" + sign);
    System.out.println("flag-------------->" + flag);
    String targetFilePath = homeDirectory.getAbsolutePath() + "\\sign.txt";
    WriteFile writeSignFile = new WriteFile();
    writeSignFile.write(targetFilePath,sign);
  }
}
