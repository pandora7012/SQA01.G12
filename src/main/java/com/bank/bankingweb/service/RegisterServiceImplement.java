package com.bank.bankingweb.service;

import com.bank.bankingweb.entity.Account;
import com.bank.bankingweb.entity.Customer;
import com.bank.bankingweb.repository.AccountRepository;
import com.bank.bankingweb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImplement implements RegisterService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Customer getCustomerByUsername(String username) {
        return customerRepository.findCustomerByUsername(username);
    }

    @Override
    public Customer getCustomerByCard(String card) {
        return customerRepository.findCustomerByCard(card);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public long countCustomer() {
        return customerRepository.count();
    }
}
