package com.linkplusit.bankapp.repository;

import com.linkplusit.bankapp.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> { }
