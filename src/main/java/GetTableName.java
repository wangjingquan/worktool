import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * @author jingquanwang
 * @date 2018/1/31
 */
public class GetTableName {

  public static void main(String[] args) {
  /*  DateTime now = new DateTime();
    DateTime dateTime =  now.plusDays(7);
    DateTime beginTime = dateTime.millisOfDay().withMinimumValue();
    DateTime endTime = dateTime.millisOfDay().withMaximumValue();*/

    DateTime nowTime = new DateTime();
    DateTime endTime = nowTime.minusDays(28);
    DateTime dateTime = new DateTime().withTimeAtStartOfDay();
    String timeStr = dateTime.toString("yyyy-MM-dd HH:mm:ss");
    DateTime deadlineTime = nowTime.plusDays(3).millisOfDay().withMinimumValue();
    DateTime dssd = nowTime.plusHours(32);
//    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//    String feess= format.format(new Date(System.currentTimeMillis() + 1000L * 3600 * 24));
    /*String canalId = "1111";
    int index = Math.abs(canalId.hashCode()) % 10 + 1;
    String tableName = "vip_add_ppcard_" + index;
    System.out.println(tableName);
    double fee = 20.01;
    String feess = String.valueOf(fee);*/
    System.out.println(dssd.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
  }
}
