import java.sql.*;

public class JdbcQuery {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            //通过驱动管理类获取数据库链接
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8","root", "root");

            //定义 sql 语句
            String sql = "select * from user ";


            //获取预处理 statement
            preparedStatement = connection.prepareStatement(sql);

            //向数据库发出 sql 执行查询，查询出结果集
            resultSet = preparedStatement.executeQuery();


            //遍历查询结果集
            while(resultSet.next()){
                System.out.println(resultSet.getString("id")+":"+resultSet.getString("username"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
