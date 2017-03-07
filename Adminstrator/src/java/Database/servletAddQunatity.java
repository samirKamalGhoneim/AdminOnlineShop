package Database;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author beshoy
 */
public class servletAddQunatity extends HttpServlet {

    DataBaseHandler instance = DataBaseHandler.getinstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Database logic = new Database();

        int id = Integer.parseInt(request.getParameter("id"));
        int qunt = Integer.parseInt(request.getParameter("qunt"));
        if (instance.editQuantity(id, qunt) != 0) {

            request.getRequestDispatcher("/AddQuantity.jsp").forward(request, response);
        }

    }

}
