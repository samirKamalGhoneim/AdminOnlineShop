package servlets;

import Database.DataBaseHandler;
import dto.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Samir
 */
@WebServlet(name = "AllUsersServlet", urlPatterns = {"/GetAllUsers"})
public class GetAllUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DataBaseHandler databaseRef = DataBaseHandler.getinstance();
        ArrayList<User> usersList = databaseRef.getAllUsers();
        request.setAttribute("usersList", usersList);
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/ShowAllUsers.jsp");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

}
