package fileutil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author jingquanwang
 * @date 2017/11/17
 */
public class BufferedInputFile {

  public String read(String fileName) throws IOException {
    BufferedReader in = new BufferedReader(new FileReader(fileName));
    String s;
    StringBuilder sb = new StringBuilder();
    while ((s = in.readLine()) != null) {
      sb.append(s + "\n");
    }
    in.close();
    return sb.toString();
  }

}
