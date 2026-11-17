package com.banking.bank.service;

import com.banking.bank.entity.Account;
import com.banking.bank.entity.Transaction;
import com.banking.bank.entity.User;
import com.banking.bank.repository.AccounRepository;
import com.banking.bank.repository.TransactionRepository;
import com.banking.bank.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final AccounRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AdminService(
            UserRepository userRepository,
            AccounRepository accountRepository,
            TransactionRepository transactionRepository) {

        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<User> getCustomers() {
        return userRepository.findAll();
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Account block(Long id) {

        Account account = getAccount(id);

        account.setStatus("BLOCKED");

        return accountRepository.save(account);
    }

    public Account unblock(Long id) {

        Account account = getAccount(id);

        account.setStatus("ACTIVE");

        return accountRepository.save(account);
    }

    public Account approve(Long id) {

        Account account = getAccount(id);

        account.setStatus("ACTIVE");

        return accountRepository.save(account);
    }

    public Account reject(Long id) {

        Account account = getAccount(id);

        account.setStatus("REJECTED");

        return accountRepository.save(account);
    }

    private Account getAccount(Long id) {

        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account not found"
                        ));
    }
}