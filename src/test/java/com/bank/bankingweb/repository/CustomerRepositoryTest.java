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

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void TestfindCustomerByUsernameAndPassword_Right() {
        String username = "1";
        String password = "1";
        Customer customer = customerRepository.findCustomerByUsernameAndPassword(username,password);
        Assertions.assertNotNull(customer);
    }

    @Test
    void TestgetfindCustomerByUsernameAndPassword_WrongMissingPassword() {
        String username = "1";
        String password = "";
        Customer customer = customerRepository.findCustomerByUsernameAndPassword(username,password);
        Assertions.assertNull(customer);
    }

    @Test
    void TestfindCustomerByUsernameAndPassword_WrongMissingUsername() {
        String username = "";
        String password = "1";
        Customer customer = customerRepository.findCustomerByUsernameAndPassword(username,password);
        Assertions.assertNull(customer);
    }
    @Test
    void TestfindCustomerByUsernameAndPassword_WrongMissingBoth() {
        String username = "";
        String password = "";
        Customer customer = customerRepository.findCustomerByUsernameAndPassword(username,password);
        Assertions.assertNull(customer);
    }
    @Test
    void TestfindCustomerByUsernameAndPassword_Wrong() {
        String username = "abc";
        String password = "xyz";
        Customer customer = customerRepository.findCustomerByUsernameAndPassword(username,password);
        Assertions.assertNull(customer);
    }

    @Test
    void TestfindCustomerByUsername_Right() {
        String username = "1";
        Customer customer = customerRepository.findCustomerByUsername(username);
        Assertions.assertNotNull(customer);
    }

    @Test
    void TestfindCustomerByUsername_Wrong() {
        String username = "abc";
        Customer customer = customerRepository.findCustomerByUsername(username);
        Assertions.assertNull(customer);
    }

    @Test
    void TestfindCustomerByCard_Right() {
        String card = "1";
        Customer customer = customerRepository.findCustomerByCard(card);
        Assertions.assertNotNull(customer);
    }

    @Test
    void TestfindCustomerByCard_Wrong() {
        String card = "123";
        Customer customer = customerRepository.findCustomerByCard(card);
        Assertions.assertNull(customer);
    }

    @Test
    void TestfindAll() {
        List<Customer> list = customerRepository.findAll();
        Assertions.assertNotNull(list);
    }

    @Test
    void Testsave_Right(){
        Customer customer = new Customer("4","4","04/04/2004",false,"4","4","4","4","4");
        Customer actualCustomer = customerRepository.save(customer);
        Assertions.assertNotNull(actualCustomer);
    }

    @Test
    void Testcount(){
        long countCustomer = customerRepository.count();
        Assertions.assertNotEquals(0,countCustomer);
    }
}