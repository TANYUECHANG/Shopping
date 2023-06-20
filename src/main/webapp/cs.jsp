<%@ page import="shopping_Cart.Cake" %>
<%@ page import="shopping_Cart.CakeDB" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="javax.servlet.annotation.WebServlet" %>
<%@ page import="java.io.IOException" %>
<%@ page import="Shopping_Flower.FlowerDB" %>
<%@ page import="Shopping_Flower.Flower" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: 谭跃昌
  Date: 2022/5/17
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    response.setContentType("text/html;charset=utf-8");
    List<Flower> cart=null;
    boolean pruFlag=true;
    HttpSession session1 =request.getSession(false);
    if(session1 ==null){
        pruFlag=false;
    }
    else{
        cart=(List) session1.getAttribute("carts");
        if(cart==null){
            pruFlag=false;
        }
    }
    if(!pruFlag){
        out.println("对不起！您还没有购买鲜花！<br>");
    }
    else{
        HashMap<String, Integer> map = new HashMap<String,Integer>();
        out.println("您购买的鲜花有：<br>");
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
            out.println(key+" 数量："+map.get(key)+"<br>");
        }
        out.println("总价格为："+price);
    }
%>
</body>
</html>
