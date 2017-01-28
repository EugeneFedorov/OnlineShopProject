package servlet;

import dto.GoodsDto;
import service.GoodsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by laonen on 26.01.2017.
 */
@WebServlet("/admin")
public class admin extends HttpServlet {
    private RequestDispatcher requestDispatcher = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String goodsName = req.getParameter("goodsName");
        String description = req.getParameter("description");
        double price = toDouble(req.getParameter("price"));
        double quantity = toDouble(req.getParameter("quantity"));
        GoodsDto goodsDto = new GoodsDto(goodsName, description, price, quantity);
        GoodsService goodsService = GoodsService.getInstance();
        if (goodsService.addGoods(goodsDto)) {
            requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/goodsAddSuccessful.jsp");
        } else {
            requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/goodsAddFail.jsp");
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
