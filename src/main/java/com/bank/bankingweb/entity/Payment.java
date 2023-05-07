package com.bank.bankingweb.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loanid")
    private Loan loan;
    private BigInteger amountPerMonth;
    private BigInteger interestPerMonth;
    private BigInteger fine;
    private boolean state;
    private String paymentDate;
    private BigInteger paymentAmount;
    private String payDate;

    public Payment() {
    }

    public Payment(Long id, Loan loan, BigInteger amountPerMonth, BigInteger interestPerMonth, BigInteger fine, boolean state, String paymentDate, BigInteger paymentAmount, String payDate) {
        this.id = id;
        this.loan = loan;
        this.amountPerMonth = amountPerMonth;
        this.interestPerMonth = interestPerMonth;
        this.fine = fine;
        this.state = state;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.payDate = payDate;
    }

    public Payment(Loan loan, BigInteger amountPerMonth, BigInteger interestPerMonth, BigInteger fine, boolean state, String paymentDate, BigInteger paymentAmount, String payDate) {
        this.loan = loan;
        this.amountPerMonth = amountPerMonth;
        this.interestPerMonth = interestPerMonth;
        this.fine = fine;
        this.state = state;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.payDate = payDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public BigInteger getAmountPerMonth() {
        return amountPerMonth;
    }

    public void setAmountPerMonth(BigInteger amountPerMonth) {
        this.amountPerMonth = amountPerMonth;
    }

    public BigInteger getInterestPerMonth() {
        return interestPerMonth;
    }

    public void setInterestPerMonth(BigInteger interestPerMonth) {
        this.interestPerMonth = interestPerMonth;
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

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigInteger getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigInteger paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

}
