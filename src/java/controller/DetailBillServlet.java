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
import jakarta.servlet.http.HttpSession;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Customer;
import model.Loan;
import model.Payment;

/**
 *
 * @author Admin
 */
@WebServlet(name="DetailBillServlet", urlPatterns={"/detailbill"})
public class DetailBillServlet extends HttpServlet {
   
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
            out.println("<title>Servlet DetailBillServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailBillServlet at " + request.getContextPath () + "</h1>");
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
        DAO dao = new DAO();
        Customer c = dao.getCustomer("long", "123456");
        Account a = dao.getAccountByCustomer(c);
        HttpSession session = request.getSession();
        session.setAttribute("account", a);
        String id_raw = request.getParameter("id");
        Date current_date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        try {
            int id = Integer.parseInt(id_raw);
            Loan loan = dao.getLoanByID(id);
            List<Payment> listpayment = dao.getPaymentByLoanID(id);
            for (Payment i: listpayment) {
                Date payment_date = sdf.parse(i.getPayment_date());
                long period = (current_date.getTime() - payment_date.getTime()) / (24*60*60*1000);
                BigInteger total = i.getAmount_per_month().add(i.getInterest_per_month());
                if (period > 0 && i.getPayment_amount().compareTo(total) < 0) {
                    i.setFine(interest_fine(period, total.subtract(i.getPayment_amount())));
                } 
            }
            dao.updatePaymentByTime(listpayment);
            BigInteger sum = BigInteger.ZERO;
            for (Payment i: listpayment) {
                sum = sum.add(i.getAmount_per_month()).add(i.getInterest_per_month()).add(i.getFine());
            }
            request.setAttribute("loan", loan);
            request.setAttribute("sum", sum);
            request.setAttribute("end_time", listpayment.get(listpayment.size() - 1).getPayment_date());
            request.setAttribute("listpayment", listpayment);
        } catch (NumberFormatException e) {
        
        } catch (ParseException e) {
            
        }
        request.getRequestDispatcher("detailbill.jsp").forward(request, response);
    } 

    private BigInteger interest_fine(long period, BigInteger amount_debt) {
        BigInteger fine = amount_debt;
        if (period >= 1 && period <= 4 ) {
            fine = amount_debt.multiply(BigInteger.valueOf(5)).divide(BigInteger.valueOf(100));
            if (fine.compareTo(BigInteger.valueOf(50000)) < 0) {
                fine = BigInteger.valueOf(50000);
            }
        } else if (period >= 5 && period <= 9) {
            fine = amount_debt.multiply(BigInteger.valueOf(10)).divide(BigInteger.valueOf(100));
            if (fine.compareTo(BigInteger.valueOf(100000)) < 0) {
                fine = BigInteger.valueOf(100000);
            }
        } else if (period >= 10 && period <= 14) {
            fine = amount_debt.multiply(BigInteger.valueOf(15)).divide(BigInteger.valueOf(100));
            if (fine.compareTo(BigInteger.valueOf(150000)) < 0) {
                fine = BigInteger.valueOf(150000);
            }
        } else {
            fine = amount_debt.multiply(BigInteger.valueOf(20)).divide(BigInteger.valueOf(100));
            if (fine.compareTo(BigInteger.valueOf(200000)) < 0) {
                fine = BigInteger.valueOf(200000);
            }
        }
        return fine;
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
