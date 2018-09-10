import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fileutil.BufferedInputFile;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author jingquanwang
 * @date 2018/6/25
 */
public class postmanCaseConverter {

  public static void main(String[] args) {
    String filedir = "C:\\Users\\jingquanwang\\Desktop\\VipApi.postman_collection.json";
    String fileOut = "C:\\Users\\jingquanwang\\Desktop\\testss.csv";
    String collectionJson = "";
    try {
      BufferedInputFile bufferedInputFile = new BufferedInputFile();
      collectionJson = bufferedInputFile.read(filedir);
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileOut));
//      OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(fileOut),"GBK");
//      BufferedWriter bufferedWriter = new BufferedWriter(writerStream);
      JSONObject postmanCaseJson = JSONObject.parseObject(collectionJson);
      JSONArray item1 = postmanCaseJson.getJSONArray("item");
      int k = 1;
      for (int i = 0; i < item1.size(); i++) {
        String moduleName = item1.getJSONObject(i).getString("name");
        System.out.println("模块名：" + moduleName);
        JSONArray item2 = item1.getJSONObject(i).getJSONArray("item");
        for (int j = 0; j < item2.size(); j++) {
          JSONArray rep = item2.getJSONObject(j).getJSONArray("response");
          if (rep.size() > 0) {
            JSONObject repJSONObject = rep.getJSONObject(0);
            String name = repJSONObject.getString("name");
            String url = repJSONObject.getJSONObject("originalRequest").getJSONObject("url").getString("raw");
            String body = repJSONObject.getString("body");


            String cvsFile = name.concat("                                  ").concat(",").concat(body);
            write(bufferedWriter, cvsFile);
            System.out.println("案例名：" + cvsFile);
//          System.out.println("请求" + cvsFile);
//          System.out.println("响应" + body);
            System.out.println(k++ + ".......................................................");
          }
        }
      }
      bufferedWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void write(BufferedWriter bufferedWriter, String content) throws IOException {
    bufferedWriter.write(content);
    bufferedWriter.newLine();
    bufferedWriter.flush();
  }
}
