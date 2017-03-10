/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminservlet;

import Database.DataBaseHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author BOSHA
 */
@WebServlet(name = "AddCards", urlPatterns = {"/AddCards"})
public class AddCards extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBaseHandler instance = DataBaseHandler.getinstance();
        boolean flag = instance.addRechargeCards(Integer.parseInt(req.getParameter("number")), Integer.parseInt(req.getParameter("balance")));
        if (flag) {
            req.setAttribute("success", "yes");
            req.getRequestDispatcher("AddRechargeCard.jsp").forward(req, resp);
            
        } else {
            req.removeAttribute("success");
            req.getRequestDispatcher("AddRechargeCard.jsp").forward(req, resp);
        }
    }
    
}
