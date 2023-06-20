package Shopping_Flower;

import shopping_Cart.Cake;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Shopping_CartDemo {
    static String jdbcName="com.mysql.cj.jdbc.Driver";
    static String dbUserName="root";
    static String dbPassword="547080";
    static String dbUrl="jdbc:mysql://localhost:3306/my?&useSSL=false&serverTimezone=UTC";
    static String sql="select * from flower";
    static Connection con=null;
    static Statement stmt=null;
    static ResultSet rs=null;
    static Map<String, Flower> List=new HashMap<String,Flower>();

    public Shopping_CartDemo() throws ClassNotFoundException, SQLException {
        Class.forName(jdbcName);
        con= DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        stmt=con.createStatement();
        rs=stmt.executeQuery(sql);
    }

    public Map getFlowers() throws SQLException {
        while(rs.next()){
            Flower flower=new Flower();
            flower.setName(rs.getString("name"));
            flower.setPrice(rs.getDouble("price"));
            flower.setId(rs.getString("id"));
            List.put(flower.getId(),flower);
        }
        return List;
    }

}
