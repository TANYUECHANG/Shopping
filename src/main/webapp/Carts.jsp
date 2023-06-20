<%@ page import="Shopping_Flower.Flower" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="Shopping_Flower.FlowerDB" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: 谭跃昌
  Date: 2022/6/15
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
</head>
<style>
    div.biaoti{
        height: 20px;
    }
    div.bt1{
        float: left;
        height: 20px;
        width: 30%;
        text-align: center;
    }
    div.bt2{
        float: left;
        height: 20px;
        width: 10%;
        text-align: center;
    }
    div.bt3{
        float: left;
        height: 20px;
        width: 10%;
        text-align: center;
    }
    div.bt4{
        float: left;
        height: 20px;
        width: 50%;
        text-align: center;
    }
    div.dh1{
        width: 30%;
        float: left;
        text-align: center;
    }
    div.dh2{
        width: 10%;
        float: left;
        text-align: center;
    }
    div.dh4{
        width: 50%;
        float: left;
        text-align: center;
    }
    div.zj{
        width: 10%;
        float: right;
        text-align: center;
    }
    input.num{
        text-align: center;
    }
</style>
<body>
<%
    response.setContentType("text/html;charset=utf-8");
    List<Flower> cart=null;
    boolean pruFlag=true;
    double price=0;
    HttpSession session1 =request.getSession(false);
    HashMap<String, Integer> map = new HashMap<String,Integer>();
    HashMap<String, Double> map2 = new HashMap<String,Double>();
    HashMap<String, Integer> map3 = new HashMap<String,Integer>();
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
        for (Flower flower:cart) {
            String name=flower.getName();
            String id=flower.getId();
            double price1= flower.getPrice();
            if(!map.containsKey(name)){
                map.put(name,1);
                map2.put(id,price1);
                map3.put(id,1);
            }
            else{
                int num=map.get(name);
                map.put(name,++num);
                map3.put(id,++num);
            }
            price+= flower.getPrice();
        }
    }
%>
<div class="biaoti">
    <div class="bt1">
        商品名称
    </div>
    <div class="bt2">
        价格
    </div>
    <div class="bt3">
        数量
    </div>
    <div class="bt4" >
        操作
    </div>
</div>
<div class="dh1">
    <%
        for(String key: map.keySet()){
            out.println(key+"<br>");
        }
    %>
</div>
<div class="dh2">
    <%
        for(String key: map2.keySet()){
            out.println(map2.get(key)+"<br>");
        }
    %>
</div>
<div class="dh2">
    <%
        for(String key: map3.keySet()){
            out.println("<input type=\"text\" value=\""+map3.get(key)+"\" class=\"num\" id=\""+key+"\"><br>");
        }
    %>
</div>
<div class="dh4">
    <%
        for(String key: map2.keySet()){
            out.println("<input value=\"-\" type=\"button\" onclick=\"fun1(" + key+","+map2.get(key)+ ")\">\n" +
                    "<input value=\"+\" type=\"button\" onclick=\"fun2(" + key+","+map2.get(key)+ ")\">" + "<br>");
        }
    %>

</div>

<script>
    function fun1(id,price) {
        if(document.getElementById(id).value>=1){
            --document.getElementById(id).value;
            let num=document.getElementById("reset-button").value-Number(price);
            document.getElementById("reset-button").value=Number(num);
        }
    }
    function fun2(id,price) {
        let num = Number(document.getElementById("reset-button").value)+Number(price);
        ++document.getElementById(id).value;
        document.getElementById("reset-button").value=Number(num);
    }
</script>
<div class="zj">
    总价格：
    <input type="text" value=<%=price%> id="reset-button">
</div>
<div class="tj"><a href="http://localhost:63342/Shopping/HTML/Login.html">提交</a></div>
</body>
</html>
