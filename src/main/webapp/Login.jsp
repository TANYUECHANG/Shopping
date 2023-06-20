<%--
  Created by IntelliJ IDEA.
  User: 谭跃昌
  Date: 2022/5/26
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        body {
            background: url("https://tse2-mm.cn.bing.net/th/id/OIP-C.PaETjVBJepDCxV7bb9QSCgHaE7?w=267&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7.jsp") no-repeat;
            background-size: 100% 150%;
        }

        #login_box {
            width: 20%;
            height: 200px;
            background-color: #00000060;
            margin: 10% auto auto;
            text-align: center;
            border-radius: 10px;
            padding: 50px 50px;
        }

        h2 {
            color: #ffffff90;
            margin-top: 5%;
        }

        #input-box {
            margin-top: 5%;
        }

        span {
            color: #fff;
        }

        input {
            border: 0;
            width: 60%;
            font-size: 15px;
            color: #fff;
            background: transparent;
            border-bottom: 2px solid #fff;
            padding: 5px 10px;
            outline: none;
            margin-top: 10px;
        }

        button {
            margin-top: 50px;
            width: 60%;
            height: 30px;
            border-radius: 10px;
            border: 0;
            color: #fff;
            text-align: center;
            line-height: 30px;
            font-size: 15px;
            background-image: linear-gradient(to right, #30cfd0, #330867);
        }

        #sign_up {
            margin-top: 45%;
            margin-left: 60%;
        }

        a {
            color: #b94648;
        }
    </style>
</head>

<body>
<div id="login_box">
    <h2>LOGIN</h2>
    <form method="post" action="http://localhost:8080/Shopping_war_exploded/LoginServlet">
        <div id="input_box">
            账户：<input type="text"  name=username placeholder="请输入用户名">
        </div>
        <div class="input_box">
            密码：<input type="password" name=password placeholder="请输入密码">
        </div>
        <button>登录</button><br>
    </form>
    <script>
        alert("密码或者账户错误！");
    </script>
</div>
</body>
</html>
