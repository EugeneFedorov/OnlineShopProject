package servlet;

import dto.LoginDto;
import service.LoginService;

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
@WebServlet("/login")
public class login extends HttpServlet {
    private static final String ADMIN = "admin";
    private static final String USER = "user";

    private RequestDispatcher requestDispatcher = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestDispatcher = req.getRequestDispatcher("WEB-INF/jsp/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username");
        String pwd = req.getParameter("password");
        LoginDto loginDto = new LoginDto(user, pwd);
        LoginService loginService = LoginService.getInstance();
        if (loginService.isExist(loginDto)) {
            if (loginService.getRole(loginDto).equalsIgnoreCase(ADMIN)) {
                requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
                req.getSession().setAttribute(ADMIN, user);
            } else {
                requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/main.jsp");
                req.getSession().setAttribute(USER, user);
            }
        } else {
            requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        }
        requestDispatcher.forward(req, resp);
    }
}
