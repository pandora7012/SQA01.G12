package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigInteger;

/**
 *
 * @author Admin
 */
public class CalculationServlet extends HttpServlet {
   
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
            out.println("<title>Servlet CalculationServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CalculationServlet at " + request.getContextPath () + "</h1>");
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
        //processRequest(request, response);
        request.getRequestDispatcher("calculation.jsp").forward(request, response);
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
        //processRequest(request, response);
        String amount_raw = request.getParameter("amount");
        String time_raw = request.getParameter("time");
        try {
            BigInteger amount = new BigInteger(amount_raw);
            int time = Integer.parseInt(time_raw);
            BigInteger root_per_month = amount.divide(BigInteger.valueOf(time));
            BigInteger interest_per_month = amount.multiply(BigInteger.valueOf(interest(time))).divide(BigInteger.valueOf(100)).divide(BigInteger.valueOf(time));
            BigInteger total_per_month = root_per_month.add(interest_per_month);
            request.setAttribute("time", time);
            request.setAttribute("interest", interest(time));
            request.setAttribute("root_per_month", root_per_month);
            request.setAttribute("interest_per_month", interest_per_month);
            request.setAttribute("total_per_month", total_per_month);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Hãy nhập số tiền mong muốn!");
        }
        request.getRequestDispatcher("calculation.jsp").forward(request, response);
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
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
