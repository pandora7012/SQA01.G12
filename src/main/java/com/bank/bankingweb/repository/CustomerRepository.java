package com.bank.bankingweb.repository;

import com.bank.bankingweb.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByUsernameAndPassword(String username, String password);
    Customer findCustomerByUsername(String username);
    Customer findCustomerByCard(String card);
    long count();

}
