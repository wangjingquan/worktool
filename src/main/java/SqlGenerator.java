/**
 * @author jingquanwang
 */
public class SqlGenerator

{

  public static void main(String[] args) {
    String tableNamePrefix = "t";
    String tableNameStem = "order";
    String tableNameSuffix = "master";
    String tableFullName = "";
    // 列名
    String columnName = "order_no";
    // 分割付
    String delimiter = "_";
    StringBuilder generateSql = new StringBuilder();
    String keyword = "4722000128";
    // 开始数字
    int beginNo = 0;
    // 结束数字
    int tableCount = 19;

    for (int i = beginNo; i <= tableCount; i++) {
      // tableFullName : t_order_master19
      tableFullName = tableNamePrefix + delimiter + tableNameStem + delimiter + tableNameSuffix
          + Integer.toString(i);
      if (i == 19) {
        generateSql = generateSql.append("select * from ").append(tableFullName).append(" where ")
            .append(columnName).append("= '").append(keyword).append("';");
      } else {
        generateSql = generateSql.append("select * from ").append(tableFullName).append(" where ")
            .append(columnName).append("= '").append(keyword).append("' union all \n");
      }
    }
    System.out.println(generateSql);
  }
}
