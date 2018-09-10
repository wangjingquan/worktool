import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


/**
 * @author jingquanwang
 */
public class PhoneGenerator {

  public static void main(String[] args) {

    Random r = new Random();
    Set<Integer> uniqueNumb = new HashSet<Integer>();
    int uniqueNumbLength = 8;
    do {
      int randomNumb = r.nextInt(99999999);
      if (Integer.toString(randomNumb).length() == uniqueNumbLength) {
        uniqueNumb.add(randomNumb);
      }
    } while (uniqueNumb.size() < 100);
    System.out.println(uniqueNumb.size());

    List<String> phones = new ArrayList<>();
    for (Integer numb : uniqueNumb) {
      String phone = "185" + numb;
      phones.add(phone);
    }
    System.out.println(phones.size());

    File file = new File("c:\\Users\\jingquanwang\\Desktop\\phones1026001.txt");
    FileOutputStream out = null;
    try {
      out = new FileOutputStream(file, true);
    } catch (FileNotFoundException e) {
      System.out.println("创建文件出错");
    }
    try {
      for (int i = 0; i < phones.size(); i++) {
        out.write(phones.get(i).toString().getBytes());
        out.write("\r\n".getBytes());
      }
      out.flush();
    } catch (IOException e) {
      System.out.println("写文件出错");
      e.printStackTrace();
    }
  }


}

