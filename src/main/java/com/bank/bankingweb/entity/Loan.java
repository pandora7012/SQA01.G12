package com.bank.bankingweb.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "loan")
public class Loan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountid")
    private Account account;
    @Column(nullable = false)
    private BigInteger amount;
    @Column(nullable = false)
    private String paymentForm;
    @Column(nullable = false)
    private int loanTime;
    @Column(nullable = false)
    private double interestRate;
    @Column(nullable = false)
    private String beginDate;
    @Column(nullable = false)
    private boolean state;

    public Loan() {
    }

    public Loan(Long id, Account account, BigInteger amount, String payment_form, int loan_time, double interest_rate, String begin_date, boolean state) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.paymentForm = payment_form;
        this.loanTime = loan_time;
        this.interestRate = interest_rate;
        this.beginDate = begin_date;
        this.state = state;
    }

    public Loan(Account account, BigInteger amount, String payment_form, int loan_time, double interest_rate, String begin_date, boolean state) {
        this.account = account;
        this.amount = amount;
        this.paymentForm = payment_form;
        this.loanTime = loan_time;
        this.interestRate = interest_rate;
        this.beginDate = begin_date;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getPayment_form() {
        return paymentForm;
    }

    public void setPayment_form(String payment_form) {
        this.paymentForm = payment_form;
    }

    public int getLoan_time() {
        return loanTime;
    }

    public void setLoan_time(int loan_time) {
        this.loanTime = loan_time;
    }

    public double getInterest_rate() {
        return interestRate;
    }

    public void setInterest_rate(double interest_rate) {
        this.interestRate = interest_rate;
    }

    public String getBegin_date() {
        return beginDate;
    }

    public void setBegin_date(String begin_date) {
        this.beginDate = begin_date;
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
