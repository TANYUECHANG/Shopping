<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form method="post" action="http://localhost:8080/Shopping_war_exploded/TestServlet">
    <div id="input_box">
        账户：<input type="text"  name=name placeholder="请输入用户名">
    </div>
    <div class="input_box">
        密码：<input type="text" name=msg placeholder="请输入密码">
    </div>
    <button>登录</button><br>
</form>
</body>
</html>