package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by laonen on 30.01.2017.
 */
@WebServlet("/main/download")
public class download extends HttpServlet {
    private static final int BYTES_DOWNLOAD = 4096;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpServletRequest request = ((HttpServletRequest) req);
        String username = request.getSession().getAttribute("user").toString();

        Enumeration<String> enumeration = req.getParameterNames();
        Map<Long, Double> goodsOrder = new HashMap<>();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            Long id = Long.valueOf(name);
            Double amount = toDouble(req.getParameter(name));
            goodsOrder.put(id, amount);
        }


        new MakeFile().makeFile(goodsOrder);

        resp.setContentType("text/plain");
        resp.setHeader("Content-Disposition", "attachment;filename=goods.txt");

        ServletContext ctx = getServletContext();
        InputStream is = ctx.getResourceAsStream("/goods.txt");

        int read = 0;
        byte[] bytes = new byte[BYTES_DOWNLOAD];
        OutputStream os = resp.getOutputStream();

        while ((read = is.read(bytes)) != -1) {
            os.write(bytes, 0, read);
        }
        os.flush();
        os.close();
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jstl/main.jsp");
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

    static class MakeFile {
        static void makeFile(Map<Long, Double> map) throws IOException {
            File file = new File("c://Work//Repository//OnlineShop//out//artifacts//web_war_exploded", "goods.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Map.Entry entry : map.entrySet()) {
                bufferedWriter.write(entry.getKey().toString() + " - ");
                bufferedWriter.write(entry.getValue() + "\n");
            }
            fileWriter.flush();
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
        }
    }
}