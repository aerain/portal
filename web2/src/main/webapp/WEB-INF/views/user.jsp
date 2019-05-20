<%--
  Created by IntelliJ IDEA.
  User: aerain
  Date: 2019-05-17
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/user/get" method="GET">
        <p>아이디 <input type="text" name="id"></p>
        <p>이름 <input type="text" name="name"></p>
        <p>비밀번호 <input type="password" name="password"></p>
    </form>
    ${user.id}
    ${user.name}
    ${user.password}
</body>
</html>
