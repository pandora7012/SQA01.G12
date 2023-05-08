package com.bank.bankingweb.repository;

import com.bank.bankingweb.entity.Account;
import com.bank.bankingweb.entity.Customer;
import com.bank.bankingweb.repository.AccountRepository;
import com.bank.bankingweb.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class AccountRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Test
    void TestfindAccountByCustomer_CustomerHasAccount() {
        Customer customer = new Customer(2L, "2", "2", "22/02/0002", true, "2", "2", "2", "2", "2");
        Assertions.assertNotNull(customer);
        Account account = accountRepository.findAccountByCustomer(customer);
        Assertions.assertNotNull(account);
    }

    /*@Test
    void TestfindAccountByCustomer_CustomerDoesNotHasAccount() {
        Customer customer = new Customer(1L, "1", "1", "1", true, "1", "1", "1", "1", "1");
        Assertions.assertNotNull(customer);
        Account account = accountRepository.findAccountByCustomer(customer);
        Assertions.assertNull(account);
    }*/

    @Test
    void Testsave(){
        Customer customer = customerRepository.findCustomerByUsername("4");
        Account account = new Account("1", BigInteger.ZERO,customer);
        Account actualAccount = accountRepository.save(account);
        Assertions.assertNotNull(actualAccount);
    }

    @Test
    void TestfindById_Right(){
        Optional<Account> accountOptional = accountRepository.findById(2L);
        Assertions.assertTrue(accountOptional.isPresent());
    }

    @Test
    void TestfindById_Wrong(){
        Optional<Account> accountOptional = accountRepository.findById(145L);
        Assertions.assertFalse(accountOptional.isPresent());
    }
}