package com.bank.bankingweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;

@Controller
public class CalculationContoller {

    @GetMapping("/calculation")
    public String getcalculation(HttpSession session) {
        if (session.getAttribute("account") == null) return "redirect:/index";
        return "calculation";
    }

    @PostMapping("/calculation")
    public String postcalculation(@RequestParam("amount") String amount_raw,
                                  @RequestParam("time") String time_raw, Model model, HttpSession session) {
        if (session.getAttribute("account") == null) return "redirect:/index";
        try {
            if (amount_raw.isEmpty()) {
                model.addAttribute("error", "Hãy nhập số tiền mong muốn");
                return "calculation";
            }
            if (!amount_raw.matches("\\d+")) {
                model.addAttribute("error", "Số tiền mong muốn không hợp lệ");
                return "calculation";
            }
            BigInteger amount = new BigInteger(amount_raw);
            if (!limitAmount(amount).equals("OK")) {
                model.addAttribute("error", limitAmount(amount));
                return "calculation";
            }
            int time = Integer.parseInt(time_raw);
            BigInteger root_per_month = amount.divide(BigInteger.valueOf(time));
            BigInteger interest_per_month = amount.multiply(BigInteger.valueOf(interest(time))).divide(BigInteger.valueOf(100)).divide(BigInteger.valueOf(time));
            BigInteger total_per_month = root_per_month.add(interest_per_month);
            model.addAttribute("time", time);
            model.addAttribute("interest", interest(time));
            model.addAttribute("root_per_month", root_per_month);
            model.addAttribute("interest_per_month", interest_per_month);
            model.addAttribute("total_per_month", total_per_month);
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Lỗi hệ thống");
        }
        return "calculation";
    }

    private String limitAmount(BigInteger amount) {
        if (amount.compareTo(new BigInteger("3000000")) < 0) {
            return "Bạn chỉ có thể vay tối thiểu 3000000 VNĐ";
        } else if (amount.compareTo(new BigInteger("500000000")) > 0) {
            return "Bạn chỉ có thể vay tối đa 500000000 VNĐ";
        } else return "OK";
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
}
