package shopping_Cart;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Shopping_CartDemo {
    static String jdbcName="com.mysql.cj.jdbc.Driver";
    static String dbUserName="root";
    static String dbPassword="547080";
    static String dbUrl="jdbc:mysql://localhost:3306/my?&useSSL=false&serverTimezone=UTC";
    static String sql="select * from cake";
    static Connection con=null;
    static Statement stmt=null;
    static ResultSet rs=null;
    static Map<String,Cake> List=new HashMap<String,Cake>();

    public Shopping_CartDemo() throws ClassNotFoundException, SQLException {
        Class.forName(jdbcName);
        con= DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
    }

    public Map getCakes() throws SQLException {
        while(rs.next()){
            Cake cake=new Cake();
            cake.setName(rs.getString("cake_name"));
            cake.setPrice(rs.getDouble("price"));
            cake.setStock(rs.getInt("stock"));
            List.put(cake.getName(),cake);
        }
        return List;
    }
}
