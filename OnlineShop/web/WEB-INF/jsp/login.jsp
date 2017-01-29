<%--
  Created by IntelliJ IDEA.
  User: laonen
  Date: 24.01.2017
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <style>
        table {
            width: 50%;
            background: white;
            color: #1f1f1f;
            border-spacing: 1px;
        }

        td, th {
            background: #86e894;
            padding: 5px;
        }
    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <table>
        <th colspan=2><h1>Авторизация</h1></th>
        <tr>
            <td><strong>Ваш логин:</strong></td>
            <td><input placeholder="Введите логин" type="text" size="20" name="username"></td>
        </tr>

        <tr>
            <td><strong>Пароль:</strong></td>
            <td><input placeholder="Введите пароль" type="password" size="20" name="password"></td>
        </tr>

        <tr>
            <td><input type="submit" value="Авторизоваться"></td>
            <td>
                <form method="get">
                    <p><input type="button" value="Зарегистрироваться"
                              onClick='location.href="http://localhost:8080/login/registration"'></p>
                </form>
            </td>
        </tr>
    </table>
</form>
</body>
</html>