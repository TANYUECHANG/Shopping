<%--
  Created by IntelliJ IDEA.
  User: 谭跃昌
  Date: 2022/6/10
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<form action="UploadServlet" method="post" enctype="multipart/form-data">
    <table width="600px">
        <tr>
            <td>上传者</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>上传文件</td>
            <td><input type="file" name="MyFile"></td>
        </tr>
    </table>
    <button>提交</button><br>
</form>
</body>
</html>
