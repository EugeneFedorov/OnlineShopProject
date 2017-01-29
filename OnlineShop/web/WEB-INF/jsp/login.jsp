<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<fmt:setBundle basename="translations"/>

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

<form>
    <input type="submit" value="en_EN" name="language">
    <input type="submit" value="ru_RU" name="language">
</form>

<form action="${pageContext.request.contextPath}/login" method="post">
    <table>
        <th colspan=2><h1><fmt:message key="login.Authorization"/></h1></th>
        <tr>
            <td><strong><fmt:message key="login.YourLogin"/>:</strong></td>
            <td><input placeholder=<fmt:message key="login.EnterLogin"/> type="text" size="20" name="username" ></td>
        </tr>

        <tr>
            <td><strong><fmt:message key="login.Password"/>:</strong></td>
            <td><input placeholder=<fmt:message key="login.EnterPassword"/> type="password" size="20" name="password" ></td>
        </tr>

        <tr>
            <td><input type="submit" value="<fmt:message key="login.login"/>"></td>
            <td>
                <form method="get">
                    <p><input type="button" value=<fmt:message key="login.SignUp"/>
                              onClick='location.href="http://localhost:8080/login/registration"'></p>
                </form>
            </td>
        </tr>
    </table>
</form>
</body>
</html>