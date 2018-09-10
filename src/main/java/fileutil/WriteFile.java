package fileutil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author jingquanwang
 * @date 2017/11/16
 */
public class WriteFile {

  public void write(String filePath,String content) throws IOException{
    File targetFile = new File(filePath);
    FileWriter targetFileWriter = new FileWriter(targetFile);
    targetFileWriter.write(content);
    targetFileWriter.flush();
    targetFileWriter.close();
  }
}
