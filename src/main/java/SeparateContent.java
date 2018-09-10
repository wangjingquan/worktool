import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import org.joda.time.DateTime;

/**
 * @author jingquanwang
 * @date 2018/3/26
 */
public class SeparateContent {

  public static void main(String[] args) throws Exception{

//    File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
    BufferedReader bufferedReader = new BufferedReader(new FileReader("E:\\username\\username.txt"));
    BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter("E:\\username\\usernameToken1.csv"));
    BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter("E:\\username\\usernameToken2.csv"));
    BufferedWriter bufferedWriter3 = new BufferedWriter(new FileWriter("E:\\username\\usernameToken3.csv"));
    BufferedWriter bufferedWriter4 = new BufferedWriter(new FileWriter("E:\\username\\usernameToken4.csv"));

    String tempString = "";
    int sum = 0;
    System.out.println(DateTime.now());
    DateTime startTime = DateTime.now();

    while((tempString=bufferedReader.readLine())!=null){
      String token = PassportUtil.generateToken(tempString);
      String result = tempString+","+ token;
      if (sum <=  4000000){
        bufferedWriter1.append(result);
        bufferedWriter1.newLine();//换行
        bufferedWriter1.flush();//需要及时清掉流的缓冲区，万一文件过大就有可能无法写入了
      }
      else if (sum >  4000000 && sum<=8000000){
        bufferedWriter2.append(result);
        bufferedWriter2.newLine();//换行
        bufferedWriter2.flush();//需要及时清掉流的缓冲区，万一文件过大就有可能无法写入了
      }else if (sum >  8000000 && sum<=12000000){
        bufferedWriter3.append(result);
        bufferedWriter3.newLine();//换行
        bufferedWriter3.flush();//需要及时清掉流的缓冲区，万一文件过大就有可能无法写入了
      }else if (sum >  12000000 && sum<=16000000){
        bufferedWriter4.append(result);
        bufferedWriter4.newLine();//换行
        bufferedWriter4.flush();//需要及时清掉流的缓冲区，万一文件过大就有可能无法写入了
      }
      System.out.println(sum++);
    }
    DateTime endTime = DateTime.now();
    System.out.println(startTime);
    System.out.println(endTime);
    bufferedReader.close();
    bufferedWriter1.close();
    bufferedWriter2.close();
    bufferedWriter3.close();
    bufferedWriter4.close();
    System.out.println(sum + " process end !");
  }
}
