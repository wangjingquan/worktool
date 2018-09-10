import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URLEncoder;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;

public class Des3Encode {

	public static void main(String[] args) throws Exception {
		String dfs = DigestUtils.md5Hex("18516773527&7017914842&14564512158&we7oad29hxo202");
		String VIP_INFO_MD5_KEY = "kasdg9hng23q4g0p223";
		//deskey.txt中index=03对应的key
		String deskey = "D046E6B6A4A85EB6C44C73372A0D5DF1AE76405173B3D5EC";
		String hexiv = "70706C6976656F6B";
		byte[] byteIv = DESUtil.hex2byte(hexiv);
		String fsd = CryptogramHelper.Encrypt("18516773527&&56&&web", deskey, byteIv);
		fsd = URLEncoder.encode(fsd,"utf-8");
		//		String ss =  CryptogramHelper.Encrypt("18917138586_170824d12", deskey, byteIv);
//		String mymd5 = DigestUtils.md5Hex(ss + VIP_INFO_MD5_KEY);
//		System.out.println(ss+","+ mymd5);

//		File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
		BufferedReader bufferedReader = new BufferedReader(new FileReader("E:\\1223压测\\yure.txt"));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("E:\\1223压测\\ustrSign.csv"));
		String tempString = null;
		int sum = 0;
		System.out.println(DateTime.now());
		DateTime startTime = DateTime.now();
		while((tempString=bufferedReader.readLine())!=null){
			String ss =  CryptogramHelper.Encrypt(tempString, deskey, byteIv);
			String mymd5 = DigestUtils.md5Hex(ss + VIP_INFO_MD5_KEY);
			bufferedWriter.append(URLEncoder.encode(ss,"utf-8") + "," + mymd5);
			bufferedWriter.newLine();//换行
			bufferedWriter.flush();//需要及时清掉流的缓冲区，万一文件过大就有可能无法写入了
			System.out.println(sum++);
		}
		DateTime endTime = DateTime.now();
		System.out.println(startTime);
		System.out.println(endTime);
		bufferedReader.close();
		bufferedWriter.close();
		System.out.println(sum + " process end !");
	}

}
