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
</head>
<body>
<form action="/login" method="post">
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
            <td colspan=2><input type="submit" value="Авторизоваться"></td>
        </tr>
        <tr>
            <td colspan=2>
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