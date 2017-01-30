<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="translations"/>
<%--
  Created by IntelliJ IDEA.
  User: laonen
  Date: 24.01.2017
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<html>
<title><fmt:message key="reg.Registration"/></title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
<body>
<form action="${pageContext.request.contextPath}/login/registration" method="post">
    <table>
        <th colspan=4>
            <span style="font-size: large; "><fmt:message key="reg.Registration"/></span>
        </th>
        <tr>
            <td><strong><fmt:message key="reg.Name"/>*:</strong></td>
            <td><label>
                <input placeholder=<fmt:message key="reg.EnterName"/> type="text" name="name" size=20 required>
            </label></td>
            <td><strong><fmt:message key="reg.Surname"/>*:</strong></td>
            <td><label>
                <input placeholder=<fmt:message key="reg.EnterSurname"/> type="text" name="surName" size=20 required>
            </label></td>
        </tr>

        <tr>
            <td><strong>E-Mail*:</strong></td>
            <td><label>
                <input placeholder=<fmt:message key="reg.EnterEmail"/> type="text" name="email" size=20 required>
            </label></td>
            <td><strong><fmt:message key="reg.Postcode"/>*:</strong></td>
            <td><label>
                <input placeholder=<fmt:message key="reg.EnterPostcode"/> type="text" name="post_index" size=20 required>
            </label></td>
        </tr>

        <tr>
            <td><strong><fmt:message key="reg.Phone"/>*:</strong></td>
            <td><label>
                <input placeholder=<fmt:message key="reg.EnterPhone"/> type="text" name="phone" size=20 required>
            </label></td>
            <td><strong><fmt:message key="reg.Country"/>*:</strong></td>
            <td><label>
                <input placeholder=<fmt:message key="reg.EnterCountry"/> type="text" name="country" size=20 required>
            </label></td>
        </tr>

        <tr>
            <td><strong><fmt:message key="reg.Town"/>*:</strong></td>
            <td><label>
                <input placeholder=<fmt:message key="reg.EnterTown"/> type="text" name="town" size=20 required>
            </label></td>
            <td><strong><fmt:message key="reg.Street"/>*:</strong></td>
            <td><label>
                <input placeholder=<fmt:message key="reg.EnterStreet"/> type="text" name="street" size=20 required>
            </label></td>
        </tr>

        <tr>
            <td><strong><fmt:message key="reg.NumberHouse"/>*:</strong></td>
            <td><label>
                <input placeholder=<fmt:message key="reg.EnterNumberHouse"/>type="text" name="numberHouse" size=20 required>
            </label></td>
            <td><strong><fmt:message key="reg.NumberFlat"/>*:</strong></td>
            <td><label>
                <input placeholder=<fmt:message key="reg.EnterNumberFlat"/> type="text" name="numberFlat" size=20 required>
            </label></td>
        </tr>

        <tr>
            <td><strong><fmt:message key="login.Password"/>*:</strong></td>
            <td><label>
                <input placeholder=<fmt:message key="login.EnterPassword"/> type="password" name="password" size=20 required>
            </label></td>
            <td><strong><fmt:message key="login.PasswordConfirmation"/>*:</strong></td>
            <td><label>
                <input placeholder=<fmt:message key="login.ConfirmPassword"/> type="password" name="password2" size=20 required>
            </label></td>
        </tr>
    </table>
    <p><input type="submit" value="Зарегистрироваться"></p>
</form>
</body>
</html>
