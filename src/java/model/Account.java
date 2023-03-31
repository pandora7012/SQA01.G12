/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author Admin
 */
public class Account implements Serializable {
    private int id;
    private String number;
    private BigInteger balance;
    private Customer customer;

    public Account() {
    }

    public Account(int id, String number, BigInteger balance, Customer customer) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
