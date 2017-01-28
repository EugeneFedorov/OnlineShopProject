<%--
  Created by IntelliJ IDEA.
  User: laonen
  Date: 25.01.2017
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin</title>
</head>
<body>
<h1>Welcome home dear admin!</h1>
<form action="/admin" method="post">
    <table>
        <tr>
            <td><strong>Наименование товара*:</strong></td>
            <td><input placeholder="Введите наименование товара" type="text" size="20" name="goodsName" required></td>
            <td><strong>Цена*:</strong></td>
            <td><input placeholder="Введите цену" type="text" size="20" name="price" required></td>

        </tr>
        <tr>
            <td><strong>Описание:</strong></td>
            <td><input placeholder="Введите описание товара" type="text" size="20" name="description"></td>
            <td><strong>Количество:</strong></td>
            <td><input placeholder="Введите количество" type="text" size="20" name="quantity"></td>
        </tr>
        <tr>
            <td colspan=2><input type="submit" value="Добавить товар"></td>
        </tr>
    </table>
</form>
</body>
</html>
