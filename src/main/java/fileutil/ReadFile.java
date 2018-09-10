package fileutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author jingquanwang
 * @date 2017/11/14
 */
public class ReadFile {

  /**
   *
   * @param fileName
   * @return
   * @throws FileNotFoundException
   * @throws IOException
   */
  public String read(String fileName) throws IOException {
    FileReader readResult = new FileReader(fileName);
    //每次读取到的字符长度
    int n;
    //用来保存每次读取到的字符
    char[] bb = new char[1024];
    //用来将每次读取到的字符拼接
    StringBuilder str = new StringBuilder();
    while ((n = readResult.read(bb)) != -1) {
      str.append(bb, 0, n);
    }
    // 关闭输入流，释放连接
    readResult.close();
    return str.toString();
  }
  public String bufferedRead(String fileName) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
  }
}
