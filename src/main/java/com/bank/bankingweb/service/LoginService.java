package com.bank.bankingweb.service;


import com.bank.bankingweb.entity.Account;
import com.bank.bankingweb.entity.Customer;

import java.util.List;

public interface LoginService {

    public Customer getCustomerByUsernameAndPassword(String username, String password);
    public Account getAccountByCustomer(Customer customer);
    public List<Customer> getAllCustomer();

}
