package com.bank.bankingweb.service;


import com.bank.bankingweb.entity.Account;
import com.bank.bankingweb.entity.Customer;
import com.bank.bankingweb.repository.AccountRepository;
import com.bank.bankingweb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImplement implements LoginService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Customer getCustomerByUsernameAndPassword(String username, String password) {
        return customerRepository.findCustomerByUsernameAndPassword(username, password);
    }

    @Override
    public Account getAccountByCustomer(Customer customer) {
        return accountRepository.findAccountByCustomer(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

}
