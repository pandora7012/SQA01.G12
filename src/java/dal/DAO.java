/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Account;
import model.Customer;
import model.Loan;
import model.Payment;

/**
 *
 * @author Admin
 */
public class DAO extends DBContext {
    
    public Customer getCustomer(String username, String password) {
        String sql = "select * from Customer where username = ? and password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer c = new Customer(rs.getInt("id"), rs.getNString("name"), rs.getNString("address"), rs.getString("id_card"), rs.getString("phonenumber"), rs.getString("email"), rs.getString("image"), rs.getString("username"), rs.getString("password"), rs.getString("dob"), rs.getBoolean("gender"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public Account getAccountByCustomer(Customer customer) {
        String sql = "select * from Account where customer_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customer.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account a = new Account(rs.getInt("id"), rs.getString("number"), rs.getBigDecimal("balance").toBigInteger(), customer);
                return a;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public Account getAccountByID(int id) {
        String sql = "select * from Account where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account a = new Account(rs.getInt("id"), rs.getString("number"), rs.getBigDecimal("balance").toBigInteger(), getCustomerByID(rs.getInt("customer_id")));
                return a;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public Customer getCustomerByID(int id) {
        String sql = "select * from Customer where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer c = new Customer(rs.getInt("id"), rs.getNString("name"), rs.getNString("address"), rs.getString("id_card"), rs.getString("phonenumber"), rs.getString("email"), rs.getString("image"), rs.getString("username"), rs.getString("password"), rs.getString("dob"), rs.getBoolean("gender"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Loan> getLoanByAccountID(int account_id) {
        List<Loan> list = new ArrayList<>();
        String sql = "select * from Loan where account_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, account_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Loan l = new Loan(rs.getInt("id"), getAccountByID(account_id), rs.getBigDecimal("amount").toBigInteger(), rs.getString("payment_form"), rs.getInt("loan_time"), rs.getDouble("interest_rate"), rs.getString("begin_date"), rs.getBoolean("state"));
                list.add(l);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public Loan getLoanByID(int id) {
        String sql = "select * from Loan where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Loan l = new Loan(rs.getInt("id"), getAccountByID(rs.getInt("account_id")), rs.getBigDecimal("amount").toBigInteger(), rs.getString("payment_form"), rs.getInt("loan_time"), rs.getDouble("interest_rate"), rs.getString("begin_date"), rs.getBoolean("state"));
                return l;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<Payment> getPaymentByLoanID(int loan_id) {
        List<Payment> list = new ArrayList<>();
        String sql = "select * from Payment where loan_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, loan_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Payment p = new Payment(rs.getInt("id"), getLoanByID(loan_id), rs.getBigDecimal("amount_per_month").toBigInteger() , rs.getBigDecimal("interest_per_month").toBigInteger(), rs.getBigDecimal("fine").toBigInteger(), rs.getBoolean("state"), rs.getString("payment_date"), (rs.getBigDecimal("payment_amount").toBigInteger() != null ? rs.getBigDecimal("payment_amount").toBigInteger() : BigInteger.ZERO), (rs.getString("pay_date") != null ? rs.getString("pay_date") : ""));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void updatePaymentByTime(List<Payment> list) {
        String sql = "update Payment set fine = ? where id = ?";
        try {
            for(Payment i: list) {
                PreparedStatement ps = connection.prepareCall(sql);
                ps.setBigDecimal(1, new BigDecimal(i.getFine()));
                ps.setInt(2, i.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void updateAccount(Account account) {
        String sql = "update Account set balance = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBigDecimal(1, new BigDecimal(account.getBalance()));
            ps.setInt(2, account.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public Payment getPayment(int id) {
        String sql = "select * from Payment where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Payment p = new Payment(rs.getInt("id"), getLoanByID(rs.getInt("loan_id")), rs.getBigDecimal("amount_per_month").toBigInteger() , rs.getBigDecimal("interest_per_month").toBigInteger(), rs.getBigDecimal("fine").toBigInteger(), rs.getBoolean("state"), rs.getString("payment_date"), (rs.getBigDecimal("payment_amount").toBigInteger() != null ? rs.getBigDecimal("payment_amount").toBigInteger() : BigInteger.ZERO), (rs.getString("pay_date") != null ? rs.getString("pay_date") : ""));
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void updatePayment(Payment payment) {
        String sql = "update Payment set pay_date = ?, payment_amount = ?, state = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, payment.getPay_date());
            ps.setBigDecimal(2, new BigDecimal(payment.getPayment_amount()));
            ps.setBoolean(3, true);
            ps.setInt(4, payment.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    } 
    
    public void updateLoan(Loan loan) {
        String sql = "update Loan set state = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, loan.isState());
            ps.setInt(2, loan.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
            
//    public static void main(String[] args) throws ParseException {
//        String date = "13/03/2023";
//        String date2 = "25/03/2023";
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
//        Date d = sdf.parse(date);
//        Date d2 = sdf.parse(date2);
//        long day = d.getTime() - d2.getTime();
//        System.out.println(d);
//        System.out.println(d2);
//        System.out.println(day/(24*60*60*1000));
//        Date current_date = new Date();
//        Date payment_date = sdf.parse("13/03/2023");
//        long period = (current_date.getTime() - payment_date.getTime()) / (24*60*60*1000);
//        System.out.println("OK: " + period);
//    }
}
