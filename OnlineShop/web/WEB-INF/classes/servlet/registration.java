package servlet;

import dto.RegistrationDto;
import service.RegistrationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by laonen on 24.01.2017.
 */
@WebServlet("/login/registration")
public class registration extends HttpServlet {
    private static final String ADMIN = "admin";
    private static final String USER = "user";

    private RequestDispatcher requestDispatcher = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surName = req.getParameter("surName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String country = req.getParameter("country");
        String town = req.getParameter("town");
        String index = req.getParameter("post_index");
        String street = req.getParameter("street");
        String numberHouse = req.getParameter("numberHouse");
        int numberFlat = Integer.valueOf(req.getParameter("numberFlat"));

        RegistrationDto dto = new RegistrationDto(name, surName, email, password, phone, country, town, index, street, numberHouse, numberFlat);
        RegistrationService instance = RegistrationService.getInstance();
        if (instance.isRegistration(dto)) {
            requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/main.jsp");
            req.getSession().setAttribute(USER, name);
            requestDispatcher.forward(req, resp);
        } else {
            requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
