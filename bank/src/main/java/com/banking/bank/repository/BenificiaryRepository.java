package com.banking.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bank.entity.Benificiary;
import java.util.List;

public interface BenificiaryRepository extends JpaRepository<Benificiary, Long> {
    List<Benificiary> findByUser_Id(Long userId);
}