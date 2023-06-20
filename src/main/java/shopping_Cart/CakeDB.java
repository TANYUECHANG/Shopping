package shopping_Cart;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CakeDB {
    private static Map<String,Cake> List=new HashMap<String,Cake>();
    private static Shopping_CartDemo shopping_cartDemo;

    public CakeDB() throws SQLException, ClassNotFoundException {
        shopping_cartDemo=new Shopping_CartDemo();
        List=shopping_cartDemo.getCakes();
    }

    public static Collection<Cake> getAll(){
        return List.values();
    }

    public static Cake getCake(String shopping_cart){
        return List.get(shopping_cart);
    }
}
