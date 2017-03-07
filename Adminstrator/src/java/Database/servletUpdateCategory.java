/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author beshoy
 */
public class servletUpdateCategory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Database logic = new Database();
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        int id = Integer.parseInt(request.getParameter("id"));
        logic.updateCategory(id, name, desc);
    }

}
