/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import model.Payment;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.JdbcQry;

/**
 *
 * @author wl2-lam
 */
public class Payment_Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int[] d = new int[16];
        String temp = "";
      
        String cardnum = request.getParameter("cardnum");
        String name =request.getParameter("name");
        float amount = Float.parseFloat(request.getParameter("amount"));
        String type = "card";
        login l = new login();
        String username=l.getUsername();
        Payment p = new Payment();
        p.setAmount(amount);
        p.setMemID(username);
        p.setTypeOfPayment(type);
        JdbcQry j= new JdbcQry( (Connection) request.getServletContext().getAttribute("connection")); 
                
        temp =request.getParameter("month");
        int month =Integer.parseInt(temp);
        String Year = request.getParameter("Year");
        int year = Integer.parseInt(Year);
        Payment c = new Payment();
        boolean b = c.credircheck(cardnum);
        Calendar now = Calendar.getInstance();
        
        if (b==true) {
            j.makePayment(p);
            response.sendRedirect("success.jsp");
        }else{
            response.sendRedirect("payment_error.jsp");
        }
        
        }      
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
