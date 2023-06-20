package Shopping_Flower;

import shopping_Cart.Cake;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name ="CartServlet",urlPatterns = "/CartServlet")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out=resp.getWriter();
        List<Flower> cart=null;
        boolean pruFlag=true;
        HttpSession session=req.getSession(false);
        if(session==null){
            pruFlag=false;
        }
        else{
            cart=(List) session.getAttribute("carts");
            if(cart==null){
                pruFlag=false;
            }
        }
        if(!pruFlag){
            out.write("对不起！您还没有购买鲜花！<br>");
        }
        else{
            HashMap<String, Integer> map = new HashMap<String,Integer>();
            out.write("您购买的鲜花有：<br>");
            double price=0;
            for (Flower flower:cart) {
                String name=flower.getName();
                if(!map.containsKey(name)){
                    map.put(name,1);
                }
                else{
                    int num=map.get(name);
                    map.put(name,++num);
                }
                price+=flower.getPrice();
            }
            for(String key: map.keySet()){
                out.write(key+" 数量："+map.get(key)+"<br>");
            }
            out.write("总价格为："+price);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request,response);
    }
}

