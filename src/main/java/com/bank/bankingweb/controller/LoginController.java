package com.bank.bankingweb.controller;

import com.bank.bankingweb.entity.Account;
import com.bank.bankingweb.entity.Customer;
import com.bank.bankingweb.entity.Loan;
import com.bank.bankingweb.entity.Payment;
import com.bank.bankingweb.service.LoanService;
import com.bank.bankingweb.service.LoginService;
import com.bank.bankingweb.service.PaymentService;
import com.bank.bankingweb.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private LoanService loanService;
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/index")
    public String index(HttpSession session) {
        List<Customer> listcustomer = loginService.getAllCustomer();
        for (Customer i: listcustomer) {
            Account a = loginService.getAccountByCustomer(i);
            if (a == null) {
                Account account = new Account(generateNumber(), BigInteger.ZERO, i);
                registerService.createAccount(account);
            }
        }
        if (session.getAttribute("account") != null) {
            Account account = (Account) session.getAttribute("account");
            try {
                List<Loan> listloan = loanService.getAllLoanByAccount(account);
                if (listloan.size() == 0) {
                    return "index";
                } else {
                    for (Loan i : listloan) {
                        List<Payment> listpayment = loanService.getAllPaymentByLoan(i);
                        if (listpayment.size() == 0) {
                            account.setBalance(account.getBalance().add(i.getAmount()));
                            List<String> payment_date = generateDates(i.getBegin_date(), i.getLoan_time());
                            List<Payment> newpayment = new ArrayList<>();
                            BigInteger amount_per_month = i.getAmount().divide(BigInteger.valueOf(i.getLoan_time()));
                            BigInteger interest_per_month = i.getAmount().multiply(BigInteger.valueOf(interest(i.getLoan_time()))).divide(BigInteger.valueOf(100)).divide(BigInteger.valueOf(i.getLoan_time()));
                            for (int j = 0; j < i.getLoan_time(); j++) {
                                newpayment.add(new Payment(i, amount_per_month, interest_per_month, BigInteger.ZERO, false, payment_date.get(j), BigInteger.ZERO, ""));
                            }
                            paymentService.createAllPayment(newpayment);
                            loanService.updateAccount(account);
                        }
                    }
                }
            } catch (Exception e) {

            }
        }
        return "index";
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

    private List<String> generateDates(String start, int time) throws Exception {
        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(start);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 13);
        for (int i = 0; i < time; i++) {
            calendar.add(Calendar.MONTH, 1);
            Date currentdate = calendar.getTime();
            String datestring = sdf.format(currentdate);
            list.add(datestring);
        }
        return list;
    }

    @GetMapping("/login")
    public String getlogin() {
        return "login";
    }

    @PostMapping("/login")
    public String postlogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpServletRequest request, HttpSession session) {
        request.getSession().invalidate();
        Customer customer = loginService.getCustomerByUsernameAndPassword(username, password);
        if (customer != null) {
            Account account = loginService.getAccountByCustomer(customer);
            request.getSession().setAttribute("account", account);
            return "redirect:/index";
        } else {
            model.addAttribute("message", "Tài khoản không tồn tại");
            return "login";
        }
    }

    private String generateNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(13);
        for (int i = 0; i < 13; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

}
