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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import model.Account;
import model.Customer;

/**
 *
 * @author Admin
 */
@WebServlet(name = "InformationServlet", urlPatterns = {"/information"})
public class InformationServlet extends HttpServlet {

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
            out.println("<title>Servlet InformationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InformationServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("information.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        try {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String id_card = request.getParameter("id_card");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String dob_raw = request.getParameter("dob");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dob_raw);
            sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dob = sdf.format(date);
            String gender_raw = request.getParameter("gender");
            boolean gender;
            if (gender_raw.equals("0")) gender = false;
            else gender = true;
            DAO dao = new DAO();
            Customer customer_check = dao.checkID_Card(id_card);
            if (customer_check == null) {
                Customer customer = new Customer(name, address, id_card, phone, email, username, password, dob, gender);
                dao.addCustomer(customer);
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("registersuccess.jsp").forward(request, response);
            } else {
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.setAttribute("message", "Tài khoản đã tồn tại");
                request.getRequestDispatcher("information.jsp").forward(request, response);
            }
        } catch (Exception e) {
            
        }
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
