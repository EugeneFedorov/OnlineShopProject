package servlet;

import dto.LoginDto;
import dto.OrderDto;
import service.LoginService;
import service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by laonen on 26.01.2017.
 */
@WebServlet("/main")
public class main extends HttpServlet {
    private static final String USER = "user";
    private static final String PWD = "pwd";

    private RequestDispatcher requestDispatcher;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> enumNames = req.getParameterNames();
        Map<Long, Double> goodsOrder = new HashMap<>();
        while (enumNames.hasMoreElements()) {
            String nameParam = enumNames.nextElement();
            Long id = Long.valueOf(nameParam);
            Double amount = toDouble(req.getParameter(nameParam));
            goodsOrder.put(id, amount);
        }
        HttpSession session = req.getSession();
        String user = session.getAttribute(USER).toString();
        String psw = session.getAttribute(PWD).toString();
        long idCustomer = LoginService.getInstance().idUser(new LoginDto(user, psw));
        OrderService instance = OrderService.getInstance();
        OrderDto orderDto = new OrderDto(idCustomer, goodsOrder);
        if (instance.isCreateOrder(orderDto)) {
            requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/createOrderSuccessful.jsp");
        } else {
            requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/createOrderFail.jsp");
        }
        requestDispatcher.forward(req, resp);
    }

    private double toDouble(String string) {
        Double result = 0.0;
        try {
            result = Double.valueOf(string);
        } catch (NumberFormatException e) {
            return result;
        }
        return result;
    }
}
