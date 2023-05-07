package com.bank.bankingweb.controller;

import com.bank.bankingweb.entity.Account;
import com.bank.bankingweb.entity.Loan;
import com.bank.bankingweb.entity.Payment;
import com.bank.bankingweb.service.BillService;
import com.bank.bankingweb.service.LoanService;
import com.bank.bankingweb.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private LoanService loanService;
    @Autowired
    private BillService billService;
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/bill")
    public String bill(HttpSession session, Model model) {
        if (session.getAttribute("account") == null) return "redirect:/index";
        Account account = (Account) session.getAttribute("account");
        try {
            List<Loan> listloan = loanService.getAllLoanByAccount(account);
            if (listloan.size() == 0) {
                model.addAttribute("message", "Tài khoản chưa thực hiện khoản vay nào");
                return "bill";
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
            session.setAttribute("listloan", listloan);
            return "bill";
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi hệ thống");
            return "bill";
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

    @GetMapping("/detailbill/{id}")
    public String detailbill(@PathVariable Long id, HttpSession session, Model model) {
        if (session.getAttribute("account") == null) return "redirect:/index";
        List<Payment> listpayment = billService.getAllPaymentByLoan(id);
        Date current_date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        try {
            for (Payment i: listpayment) {
                Date payment_date = sdf.parse(i.getPaymentDate());
                long period = (current_date.getTime() - payment_date.getTime()) / (24*60*60*1000);
                BigInteger total = i.getAmountPerMonth().add(i.getInterestPerMonth());
                if (period > 0 && i.getPaymentAmount().compareTo(total) < 0) {
                    i.setFine(interest_fine(period, total.subtract(i.getPaymentAmount())));
                }
            }
            billService.updateAllPayment(listpayment);
            BigInteger sum = BigInteger.ZERO;
            for (Payment i: listpayment) {
                sum = sum.add(i.getAmountPerMonth()).add(i.getInterestPerMonth()).add(i.getFine());
            }
            session.setAttribute("loan", loanService.getLoanById(id));
            session.setAttribute("sum", sum);
            session.setAttribute("end_time", listpayment.get(listpayment.size() - 1).getPaymentDate());
            session.setAttribute("listpayment", listpayment);
            return "detailbill";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi hệ thống");
            return "bill";
        }
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

    @GetMapping("/pay/{id}")
    public String pay(@PathVariable Long id, HttpSession session, Model model) {
        if (session.getAttribute("account") == null) return "redirect:/index";
        Payment payment = paymentService.getPaymentById(id);
        Loan loan = payment.getLoan();
        Long loan_id = payment.getLoan().getId();
        Account account = (Account) session.getAttribute("account");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            BigInteger total_amount = payment.getAmountPerMonth().add(payment.getInterestPerMonth()).add(payment.getFine());
            if (account.getBalance().compareTo(total_amount) < 0) {
                session.setAttribute("message", "Bạn không đủ tiền để thực hiện giao dịch");
                return "redirect:/detailbill/" + loan_id;
            }
            if (!checkValidTime(payment).equals("OK")) {
                session.setAttribute("message", checkValidTime(payment));
                return "redirect:/detailbill/" + loan_id;
            } else {
                payment.setPayDate(sdf.format(date));
                payment.setPaymentAmount(total_amount);
                payment.setState(true);
                session.setAttribute("balance_before", account.getBalance());
                account.setBalance(account.getBalance().subtract(total_amount));
                loanService.updateAccount(account);
                paymentService.updatePayment(payment);
                session.setAttribute("balance_after", account.getBalance());
            }
            List<Payment> list = billService.getAllPaymentByLoan(loan_id);
            int sum = 0;
            for (Payment i: list) {
                if (i.isState() == true) sum += 1;
            }
            if (sum == list.size()) {
                loan.setState(true);
                loanService.updateLoan(loan);
            }
        } catch (NumberFormatException e) {
            model.addAttribute("message", "Lỗi hệ thống");
            return "redirect:/detailbill/" + loan_id;
        }
        return "redirect:/detailbill/" + loan_id;
    }

    private String checkValidTime(Payment payment) {
        String paymendate = payment.getPaymentDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse(paymendate);
            Date currentdate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, -1);
            Date previousdate = calendar.getTime();
            if (currentdate.after(previousdate) && currentdate.before(date)) return "OK";
            else {
                return "Chưa đến hạn thanh toán khoản vay này";
            }
        } catch (Exception ex) {
            return "Lỗi hệ thống";
        }
    }

}
