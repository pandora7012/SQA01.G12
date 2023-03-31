/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Loan implements Serializable {
    private int id;
    private Account account;
    private BigInteger amount;
    private String payment_form;
    private int loan_time;
    private double interest_rate;
    private String begin_date;
    private boolean state;

    public Loan() {
    }

    public Loan(int id, Account account, BigInteger amount, String payment_form, int loan_time, double interest_rate, String begin_date, boolean state) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.payment_form = payment_form;
        this.loan_time = loan_time;
        this.interest_rate = interest_rate;
        this.begin_date = begin_date;
        this.state = state;
    }
    
    public Loan(Account account, BigInteger amount, String payment_form, int loan_time, double interest_rate, String begin_date, boolean state) {
        this.account = account;
        this.amount = amount;
        this.payment_form = payment_form;
        this.loan_time = loan_time;
        this.interest_rate = interest_rate;
        this.begin_date = begin_date;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getPayment_form() {
        return payment_form;
    }

    public void setPayment_form(String payment_form) {
        this.payment_form = payment_form;
    }

    public int getLoan_time() {
        return loan_time;
    }

    public void setLoan_time(int loan_time) {
        this.loan_time = loan_time;
    }

    public double getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(double interest_rate) {
        this.interest_rate = interest_rate;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    
}
