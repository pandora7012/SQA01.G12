package com.bank.bankingweb.controller;

import com.bank.bankingweb.entity.Account;
import com.bank.bankingweb.entity.Loan;
import com.bank.bankingweb.entity.Payment;
import com.bank.bankingweb.service.LoanService;
import com.bank.bankingweb.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class LoanController {

    @Autowired
    private LoanService loanService;
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/loan")
    public String getloan(HttpSession session) {
        if (session.getAttribute("account") == null) return "redirect:/index";
        return "loan";
    }

    @PostMapping("/loan")
    public String postloan(@RequestParam("salary") String salary,
                           @RequestParam("method") String method,
                           @RequestParam("amount") String amount_raw,
                           @RequestParam("loan_time") String time_raw, Model model, HttpSession session) {
        if (session.getAttribute("account") == null) return "redirect:/index";
        try {
            if (!amount_raw.matches("\\d+")) {
                model.addAttribute("message", "Số tiền muốn vay không hợp lệ");
                return "loan";
            }
            int time = Integer.parseInt(time_raw);
            int interest = interest(time);
            BigInteger amount = new BigInteger(amount_raw);
            if (!limitAmount(salary, amount).equals("OK")) {
                model.addAttribute("message", limitAmount(salary, amount));
                return "loan";
            }
            session.setAttribute("salary", GetMonthlyText(salary));
            session.setAttribute("amount", amount);
            session.setAttribute("time", time);
            session.setAttribute("interest", interest);
            session.setAttribute("method", GetPaymentMethod(method));
            return "redirect:/contract";
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Số tiền muốn vay không hợp lệ");
            return "loan";
        }
    }

    private String limitAmount(String salary, BigInteger amount) {
        if (amount.compareTo(new BigInteger("3000000")) < 0) {
            return "Bạn chỉ có thể vay tối thiểu 3000000 VNĐ";
        } else {
            switch (salary) {
                case "5_under":
                    if (amount.compareTo(new BigInteger("50000000")) > 0)
                        return "Bạn chỉ có thể vay tối đa 50000000 VNĐ";
                    else return "OK";
                case "5_10_between":
                    if (amount.compareTo(new BigInteger("100000000")) > 0)
                        return "Bạn chỉ có thể vay tối đa 100000000 VNĐ";
                    else return "OK";
                case "10_higher":
                    if (amount.compareTo(new BigInteger("500000000")) > 0)
                        return "Bạn chỉ có thể vay tối đa 500000000 VNĐ";
                    else return "OK";
                default:
                    if (amount.compareTo(new BigInteger("50000000")) > 0)
                        return "Bạn chỉ có thể vay tối đa 50000000 VNĐ";
                    else return "OK";
            }
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

    @GetMapping("/contract")
    public String getcontract(HttpSession session) {
        if (session.getAttribute("account") == null) return "redirect:/index";
        return "contract";
    }

    @PostMapping("/contract")
    public String postcontract(@RequestParam("interest") String interest_raw,
                               @RequestParam("method") String method,
                               @RequestParam("amount") String amount_raw,
                               @RequestParam("time") String time_raw, Model model, HttpSession session) {
        if (session.getAttribute("account") == null) return "redirect:/index";
        try {
            Account account = (Account) session.getAttribute("account");
            BigInteger amount = new BigInteger(amount_raw);
            int time = Integer.parseInt(time_raw);
            int interest = Integer.parseInt(interest_raw);
            Date current_date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(current_date);
            Loan loan = new Loan(account, amount, method, time, interest, date, false);
            loanService.createLoan(loan, account.getId());
            return "redirect:/loansuccess";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Lỗi hệ thống");
            return "contract";
        }
    }

    @GetMapping("/loansuccess")
    public String getloansuccess(HttpSession session) {
        if (session.getAttribute("account") == null) return "redirect:/index";
        return "loansuccess";
    }

    @PostMapping("/loansuccess")
    public String postloansuccess(HttpSession session, Model model) {
        return "redirect:/index";
    }

}
