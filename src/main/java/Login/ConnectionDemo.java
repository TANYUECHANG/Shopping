package Login;

import java.sql.*;
import java.util.function.DoubleToIntFunction;

public class ConnectionDemo {
    String jdbcName="com.mysql.cj.jdbc.Driver";
    String dbUserName="root";
    String dbPassword="547080";
    String dbUrl="jdbc:mysql://localhost:3306/my?&useSSL=false&serverTimezone=UTC";
    Connection con=null;
    PreparedStatement preparedStatement = null;
    ResultSet rs=null;

    public ConnectionDemo(){
        try {
            Class.forName(jdbcName);
            con= DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
            preparedStatement = con.prepareStatement("select * from user where account=?");

        } catch (ClassNotFoundException e) {
            System.out.println("驱动错误！");
        } catch (SQLException e) {
            System.out.println("连接错误！");
        }
    }

    public boolean select(String account,String password) throws SQLException {
        try {
            preparedStatement.setString(1,account);
            rs=preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("sql语句执行错误！");
        }
        while (rs.next()){
            System.out.println(rs.getString(1)+" "+rs.getString(2) );
            if (rs.getString("password").equals(password)){
                System.out.println("成功！");
                return true;
            }
        }
        return false;
    }
}
