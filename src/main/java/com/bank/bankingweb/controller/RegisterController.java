package com.bank.bankingweb.controller;


import com.bank.bankingweb.entity.Account;
import com.bank.bankingweb.entity.Customer;
import com.bank.bankingweb.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/register")
    public String getregister(HttpSession session) {
        return "register";
    }

    @PostMapping("/register")
    public String postregister(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("samepassword") String samepassword, Model model, HttpSession session) {
        Customer customer = registerService.getCustomerByUsername(username);
        if (customer != null) {
            model.addAttribute("message", "Tài khoản đã tồn tại");
            return "register";
        } else {
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            return "redirect:/information";
        }
    }

    @GetMapping("/information")
    public String getinformation(HttpSession session) {
        return "information";
    }

    @PostMapping("/information")
    public String postinformation(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  @RequestParam("gender") String gender_raw,
                                  @RequestParam("dob") String dob_raw,
                                  @RequestParam("address") String address,
                                  @RequestParam("email") String email,
                                  @RequestParam("id_card") String id_card,
                                  @RequestParam("phonenumber") String phonenumber,
                                  @RequestParam("name") String name, Model model, HttpSession session) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dob_raw);
            if (!checkValidYear(date)) {
                model.addAttribute("message", "Năm sinh không hợp lệ");
                return "information";
            } else if (!id_card.matches("\\d+") || id_card.length() != 12) {
                model.addAttribute("message", "Số CCCD không hợp lệ");
                return "information";
            } else if (!phonenumber.matches("\\d+") || (phonenumber.length() != 10 && phonenumber.length() != 11)) {
                model.addAttribute("message", "Số điện thoại không hợp lệ");
                return "information";
            } else if (!email.endsWith("@gmail.com")) {
                model.addAttribute("message", "Email không hợp lệ");
                return "information";
            } else {
                sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dob = sdf.format(date);
                boolean gender;
                if (gender_raw.equals("0")) gender = false;
                else gender = true;
                if (registerService.getCustomerByCard(id_card) != null) {
                    model.addAttribute("message", "Tài khoản đã tồn tại");
                    return "information";
                } else {
                    Customer customer = new Customer(name, address, dob, gender, id_card, phonenumber, email, username, password);

                    registerService.createCustomer(customer);
                    return "register-success";
                }
            }
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi hệ thống");
            return "information";
        }
    }

    @GetMapping("/register-success")
    public String getregistersuccess(HttpSession session) {
        return "register-success";
    }

    @PostMapping("/register-success")
    public String postregistersuccess(@RequestParam("username") String username) {
        return "redirect:/index";
    }

    private boolean checkValidYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        Calendar currentCalendar = Calendar.getInstance();
        int currentYear = currentCalendar.get(Calendar.YEAR);
        if ((year > currentYear - 18) || (year < currentYear - 100)) return false;
        else return true;
    }

}
