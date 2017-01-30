<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>

<fmt:setBundle basename="translations"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="service.GoodsService" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.GoodsDto" %><%--
  Created by IntelliJ IDEA.
  User: laonen
  Date: 24.01.2017
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>

<%
    List<GoodsDto> goodsDtoList = GoodsService.getInstance().getAllGoods();
    request.setAttribute("goods", goodsDtoList);
%>

<html>
<head>
    <title>main</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<form action="${pageContext.request.contextPath}/main" method="post">
    <table>
        <thead>
        <tr>
            <th><fmt:message key="main.Name"/></th>
            <th><fmt:message key="main.Description"/></th>
            <th><fmt:message key="main.Price"/></th>
            <th><fmt:message key="main.Amount"/></th>
            <th><fmt:message key="main.CreateOrder"/></th>
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
    <p><input type="submit" value=<fmt:message key="main.Checkout"/>></p>
</form>

<form action="${pageContext.request.contextPath}/main/download" method="get">
    <table hidden>
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
    <button type="submit"><fmt:message key="main.Download"/></button>
</form>

</body>
</html>
