package com.bank.bankingweb.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private BigInteger balance;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerid")
    private Customer customer;

    public Account(Long id, String number, BigInteger balance, Customer customer) {
        this.id = id;
        this.number = number;
        this.balance = balance;
        this.customer = customer;
    }

    public Account(String number, BigInteger balance, Customer customer) {
        this.number = number;
        this.balance = balance;
        this.customer = customer;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
