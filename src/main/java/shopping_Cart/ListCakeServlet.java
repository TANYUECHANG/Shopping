package shopping_Cart;

import shopping_Cart.Cake;
import shopping_Cart.CakeDB;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet(name = "ListCakeServlet",urlPatterns = "/ListCakeServlet")
public class ListCakeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out=resp.getWriter();
        CakeDB cakeDB= null;
        try {
            cakeDB = new CakeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Collection<Cake> cakes=cakeDB.getAll();
        out.write("本站提供的蛋糕有：<br>");
        for (Cake cake:cakes) {
            String url="PurchaseServlet?id="+cake.getName();
            out.write(cake.getName()+" 价格："+cake.getPrice()+"<a href='"+url+"'> 点击购买</a><br>");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doGet(request,response);
    }
}

