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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.Loan;
import model.Payment;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoanSuccessServlet", urlPatterns = {"/loansuccess"})
public class LoanSuccessServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoanSuccessServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoanSuccessServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        request.getRequestDispatcher("loansuccess.jsp").forward(request, response);
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
        String account_id_raw = request.getParameter("accountid");
        try {
            int account_id = Integer.parseInt(account_id_raw);
            DAO dao = new DAO();
            List<Loan> listloan = dao.getLoanByAccountID(account_id);
            Loan loan = listloan.get(listloan.size() - 1);
            List<String> payment_date = generateDates(loan.getBegin_date(), loan.getLoan_time());
            List<Payment> listpayment = new ArrayList<>();
            BigInteger amount_per_month = loan.getAmount().divide(BigInteger.valueOf(loan.getLoan_time()));
            BigInteger interest_per_month = loan.getAmount().multiply(BigInteger.valueOf(interest(loan.getLoan_time()))).divide(BigInteger.valueOf(100)).divide(BigInteger.valueOf(loan.getLoan_time()));
            for (int i = 0; i < loan.getLoan_time(); i++) {
                listpayment.add(new Payment(loan, amount_per_month, interest_per_month, BigInteger.ZERO, false, payment_date.get(i), BigInteger.ZERO, ""));
            }
            dao.addPayments(listpayment);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } catch (Exception e) {

        }
    }

    private List<String> generateDates(String start, int time) throws Exception {
        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(start);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for (int i = 0; i < time; i++) {
            calendar.add(Calendar.MONTH, 1);
            Date currentdate = calendar.getTime();
            String datestring = sdf.format(currentdate);
            list.add(datestring);
        }
        return list;
    }

    private int interest(int time) {
        switch (time) {
            case 3:
                return 5;
            case 6:
                return 8;
            case 9:
                return 10;
            case 12:
                return 12;
        }
        return 0;
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
