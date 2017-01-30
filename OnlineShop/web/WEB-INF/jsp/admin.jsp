<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="translations"/>

<html>
<head>
    <title><fmt:message key="admin.Admin"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<h1><fmt:message key="admin.Welcome"/></h1>
<form action="${pageContext.request.contextPath}/admin" method="post">
    <table>
        <tr>
            <td><strong><fmt:message key="admin.NameOfGoods"/>*:</strong></td>
            <td><input placeholder=
                       <fmt:message key="admin.InputNameOfGoods"/> type="text" name="goodsName" required></td>
            <td><strong><fmt:message key="admin.Price"/>*:</strong></td>
            <td><input placeholder=
                       <fmt:message key="admin.EnterPrice"/> type="text" name="price" required></td>

        </tr>
        <tr>
            <td><strong><fmt:message key="admin.Description"/>:</strong></td>
            <td><input placeholder=
                       <fmt:message key="admin.EnterDescription"/> type="text" name="description"></td>
            <td><strong><fmt:message key="admin.Quantity"/>:</strong></td>
            <td><input placeholder=
                       <fmt:message key="admin.EnterQuantity"/> type="text" name="quantity"></td>
        </tr>
        <tr>
            <td colspan=2><input type="submit" value=<fmt:message key="admin.AddNewGoods"/> ></td>
        </tr>
    </table>
</form>
</body>
</html>