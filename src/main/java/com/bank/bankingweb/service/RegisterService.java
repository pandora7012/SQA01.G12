package com.bank.bankingweb.service;


import com.bank.bankingweb.entity.Account;
import com.bank.bankingweb.entity.Customer;

public interface RegisterService {

    public Customer getCustomerByUsername(String username);
    public Customer getCustomerByCard(String card);
    public Customer createCustomer(Customer customer);
    public Account createAccount(Account account);
    public long countCustomer();

}
