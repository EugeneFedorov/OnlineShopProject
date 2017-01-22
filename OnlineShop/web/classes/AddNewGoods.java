import dao.GoodsDao;
import entity.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by laonen on 18.01.2017.
 */

@WebServlet("/addGoods")
public class AddNewGoods extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader bufferedReader = req.getReader();
        try {
            System.out.println(new GoodsDao().create(new Goods(bufferedReader.readLine(), bufferedReader.readLine(),
                    Double.parseDouble(bufferedReader.readLine()), Double.parseDouble(bufferedReader.readLine()))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
