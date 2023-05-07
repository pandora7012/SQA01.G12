package com.bank.bankingweb.repository;

import com.bank.bankingweb.entity.Account;
import com.bank.bankingweb.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByCustomer(Customer customer);

}
