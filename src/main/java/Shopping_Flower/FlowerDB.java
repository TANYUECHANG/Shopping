package Shopping_Flower;

import shopping_Cart.Cake;
import shopping_Cart.Shopping_CartDemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FlowerDB {
    private static Map<String, Flower> List=new HashMap<String,Flower>();
    private static Shopping_Flower.Shopping_CartDemo shopping_cartDemo;

    public FlowerDB() throws SQLException, ClassNotFoundException {
        shopping_cartDemo= new Shopping_Flower.Shopping_CartDemo();
        List=shopping_cartDemo.getFlowers();
    }

    public static Collection<Flower> getAll(){
        return List.values();
    }

    public static Flower getFlower(String id){
        return List.get(id);
    }
}
