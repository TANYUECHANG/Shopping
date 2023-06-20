package Shopping_Flower;

import shopping_Cart.Cake;
import shopping_Cart.CakeDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PurchaseServlet",urlPatterns = "/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        try {
            FlowerDB flowerDB=new FlowerDB();

        if(id==null){
            String url="ListCakeServlet";
            resp.sendRedirect(url);
            return;
        }
        Flower flower=flowerDB.getFlower(id);
        HttpSession session=req.getSession();
        List<Flower> cart=(List) session.getAttribute("carts");
        if (cart==null){
            cart=new ArrayList<Flower>();
            session.setAttribute("carts",cart);
        }
        System.out.println("购物车添加："+flower.getName());
        cart.add(flower);
        Cookie cookie=new Cookie("JSESSIONID",session.getId());
        cookie.setMaxAge(60);
        cookie.setPath("/Servlet");
        resp.addCookie(cookie);
        String url="http://localhost:8080/Shopping_war_exploded/Carts.jsp";
        resp.sendRedirect(url);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request,response);
    }
}

