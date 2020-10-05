<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-10-05
  Time: 4:42 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chat Room</title>
</head>
<body>
    <h1>Welcome to Concordia Chat Room</h1>
    <form action="/ChatServlet" method="POST">
        <input type="text" name="username">
        <input type="text" name="message">
        </br>
        <button type="button" name="download">Download Chat File</button>
        <button type="submit" name="send">Send</button>
    </form>
</body>
</html>
