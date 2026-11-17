package com.banking.bank.service;

import com.banking.bank.dto.AccountRequest;
import com.banking.bank.entity.Account;
import com.banking.bank.entity.User;
import com.banking.bank.repository.AccounRepository;
import com.banking.bank.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    private final AccounRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccounRepository accountRepository,
            UserRepository userRepository) {

        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public Account createAccount(AccountRequest request) {
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new RuntimeException("User not found"));
        Account account = new Account();
        account.setAccountNumber(
                generateAccountNumber());

        account.setAccountType(request.accountType());

        account.setBalance(BigDecimal.ZERO);

        account.setStatus("PENDING");

        account.setUser(user);

        return accountRepository.save(account);
    }

    private String generateAccountNumber() {

        return "BANK" +
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 12)
                        .toUpperCase();
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public List<Account> getUserAccounts(Long userId) {
        return accountRepository.findByUser_Id(userId);
    }
}
