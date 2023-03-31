/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigInteger;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Payment {
    private int id;
    private Loan loan;
    private BigInteger amount_per_month;
    private BigInteger interest_per_month;
    private BigInteger fine;
    private boolean state;
    private String payment_date;
    private BigInteger payment_amount;
    private String pay_date;

    public Payment() {
    }

    public Payment(int id, Loan loan, BigInteger amount_per_month, BigInteger interest_per_month, BigInteger fine, boolean state, String payment_date, BigInteger payment_amount, String pay_date) {
        this.id = id;
        this.loan = loan;
        this.amount_per_month = amount_per_month;
        this.interest_per_month = interest_per_month;
        this.fine = fine;
        this.state = state;
        this.payment_date = payment_date;
        this.payment_amount = payment_amount;
        this.pay_date = pay_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public BigInteger getAmount_per_month() {
        return amount_per_month;
    }

    public void setAmount_per_month(BigInteger amount_per_month) {
        this.amount_per_month = amount_per_month;
    }

    public BigInteger getInterest_per_month() {
        return interest_per_month;
    }

    public void setInterest_per_month(BigInteger interest_per_month) {
        this.interest_per_month = interest_per_month;
    }

    public BigInteger getFine() {
        return fine;
    }

    public void setFine(BigInteger fine) {
        this.fine = fine;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public BigInteger getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(BigInteger payment_amount) {
        this.payment_amount = payment_amount;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }
    
    
}
