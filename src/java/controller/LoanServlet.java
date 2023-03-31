/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Account;
import model.Loan;

/**
 *
 * @author Admin
 */
@WebServlet(name="LoanServlet", urlPatterns={"/loan"})
public class LoanServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoanServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoanServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("loan.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String salary = GetMonthlyText(request.getParameter("salary")) ;
        String method = GetPaymentMethod(request.getParameter("method"));
        String amount_raw = request.getParameter("amount");
        String time_raw = request.getParameter("loan_time");
        try {
            int time = Integer.parseInt(time_raw);
            int interest = interest(time);
            BigInteger amount = new BigInteger(amount_raw);
            request.setAttribute("salary", salary);
            request.setAttribute("amount", amount);
            request.setAttribute("time", time);
            request.setAttribute("interest", interest);
            request.setAttribute("method", method);
            request.getRequestDispatcher("contract.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            
        } 
    }
    
    private int interest(int time) {
        switch (time) {
            case 3: return 5;
            case 6: return 8;
            case 9: return 10;
            case 12: return 12;
        }
        return 0;
    }

    private String GetMonthlyText(String all){
        switch (all)
        {
            case "5_under":
                return "Dưới 5 triệu";
            case "5_10_between":
                return "Từ 5 đến 10 triệu";
            case "10_higher":
                return "Trên 10 triệu";
        }
        return null;
    }

    private String GetPaymentMethod(String um){

       switch (um){
            case "Cash":
                return "Tiền mặt";
            case "This_bank":
                return "Trả lương qua ngân hàng này";
            case "Other_bank":
                return "Trả lương qua ngân hàng khác";
        }
        return null;
    }
    
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
