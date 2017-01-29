<%@ page import="service.GoodsService" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.GoodsDto" %><%--
  Created by IntelliJ IDEA.
  User: laonen
  Date: 24.01.2017
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<GoodsDto> goodsDtoList = GoodsService.getInstance().getAllGoods();
    request.setAttribute("goods", goodsDtoList);
%>

<html>
<head>
    <title>main</title>
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
            width: 20%;
        }
    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/main" method="post">
    <table>
        <thead>
        <tr>
            <th>name</th>
            <th>description</th>
            <th>price</th>
            <th>amount</th>
            <th>add to order</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="g" items="${goods}">
            <tr>
                <td>${g.name}</td>
                <td>${g.description}</td>
                <td>${g.price}</td>
                <td>${g.remainingAmount}</td>
                <td><label>
                    <input type="text" name="${g.id}">
                </label></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p><input type="submit" value="Оформить заказ"></p>
</form>
</body>
</html>
