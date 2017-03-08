/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminservlet;

import Database.DataBaseHandler;
import dto.Product;
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
public class servletUpdateProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        double price = Double.parseDouble(request.getParameter("price"));
        String cat = request.getParameter("cat");
        int id = Integer.parseInt(request.getParameter("id"));
        DataBaseHandler instance = DataBaseHandler.getinstance();
        Product product = new Product(name, id, price, desc, cat);
        boolean flag = instance.editProduct(product);
    }

}
