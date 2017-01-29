<%--
  Created by IntelliJ IDEA.
  User: laonen
  Date: 24.01.2017
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
<body>
<form action="${pageContext.request.contextPath}/login/registration" method="post">
    <table>
        <th colspan=4>
            <span style="font-size: large; ">Регистрация</span>
            <span style="font-size: xx-small; ">* требуемые поля</span>
        </th>
        <tr>
            <td><strong>Имя*:</strong></td>
            <td><label>
                <input placeholder="Введите имя" type="text" name="name" size=20>
            </label></td>
            <td><strong>Фамилия*:</strong></td>
            <td><label>
                <input placeholder="Введите фамилию" type="text" name="surName" size=20>
            </label></td>
        </tr>

        <tr>
            <td><strong>E-Mail*:</strong></td>
            <td><label>
                <input placeholder="Введите e-mail" type="text" name="email" size=20>
            </label></td>
            <td><strong>Почтовый индекс*:</strong></td>
            <td><label>
                <input placeholder="Введите индекс" type="text" name="post_index" size=20>
            </label></td>
        </tr>

        <tr>
            <td><strong>Телефон*:</strong></td>
            <td><label>
                <input placeholder="Введите телефон" type="text" name="phone" size=20>
            </label></td>
            <td><strong>Страна*:</strong></td>
            <td><label>
                <input placeholder="Введите страну" type="text" name="country" size=20>
            </label></td>
        </tr>

        <tr>
            <td><strong>Город*:</strong></td>
            <td><label>
                <input placeholder="Введите город" type="text" name="town" size=20>
            </label></td>
            <td><strong>Улица*:</strong></td>
            <td><label>
                <input placeholder="Введите улицу" type="text" name="street" size=20>
            </label></td>
        </tr>

        <tr>
            <td><strong>Номер дома*:</strong></td>
            <td><label>
                <input placeholder="Введите номер дома" type="text" name="numberHouse" size=20>
            </label></td>
            <td><strong>Номер квартиры*:</strong></td>
            <td><label>
                <input placeholder="Введите номер квартиры" type="text" name="numberFlat" size=20>
            </label></td>
        </tr>

        <tr>
            <td><strong>Пароль*:</strong></td>
            <td><label>
                <input placeholder="Введите пароль" type="password" name="password" size=20>
            </label></td>
            <td><strong>Подтверждение пароля*:</strong></td>
            <td><label>
                <input placeholder="Подтвердите пароль" type="password" name="password2" size=20>
            </label></td>
        </tr>
    </table>
    <p><input type="submit" value="Зарегистрироваться"></p>
</form>
</body>
</html>
