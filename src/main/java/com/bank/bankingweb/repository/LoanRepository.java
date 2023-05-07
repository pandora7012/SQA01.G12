package com.bank.bankingweb.repository;

import com.bank.bankingweb.entity.Account;
import com.bank.bankingweb.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findAllByAccount(Account account);
    @Override
    Optional<Loan> findById(Long id);

}
