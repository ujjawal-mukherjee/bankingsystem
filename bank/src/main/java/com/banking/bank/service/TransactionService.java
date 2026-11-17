package com.banking.bank.service;

import com.banking.bank.dto.MoneyRequest;
import com.banking.bank.dto.TransferRequest;
import com.banking.bank.entity.Account;
import com.banking.bank.entity.Transaction;
import com.banking.bank.repository.AccounRepository;
import com.banking.bank.repository.TransactionRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
        private final AccounRepository accountRepository;
        private final TransactionRepository transactionRepository;

        public TransactionService(
                        AccounRepository accountRepository,
                        TransactionRepository transactionRepository) {

                this.accountRepository = accountRepository;
                this.transactionRepository = transactionRepository;
        }

        @Transactional
        public Transaction deposit(MoneyRequest request) {
                Account account = getActiveAccount(request.accountId());
                account.setBalance(account.getBalance().add(request.amount()));
                accountRepository.save(account);
                return saveTransaction(
                                account,
                                "DEPOSIT",
                                request.amount());
        }

        @Transactional
        public Transaction withdraw(MoneyRequest request) {
                Account account = getActiveAccount(request.accountId());
                if (account.getBalance().compareTo(request.amount()) < 0) {
                        throw new RuntimeException("Insufficient Balance");
                }
                account.setBalance(account.getBalance().subtract(request.amount()));
                accountRepository.save(account);
                return saveTransaction(
                                account,
                                "WITHDRAW",
                                request.amount());
        }

        @Transactional
        public void transfer(TransferRequest request) {

                Account sender = getActiveAccount(
                                request.senderAccountId());

                Account receiver = getActiveAccount(
                                request.receiverAccountId());

                if (sender.getId().equals(receiver.getId())) {

                        throw new RuntimeException(
                                        "Sender and receiver cannot be same");
                }

                if (sender.getBalance()
                                .compareTo(request.amount()) < 0) {

                        throw new RuntimeException(
                                        "Insufficient balance");
                }

                // Debit sender
                sender.setBalance(
                                sender.getBalance()
                                                .subtract(request.amount()));

                // Credit receiver
                receiver.setBalance(
                                receiver.getBalance()
                                                .add(request.amount()));

                accountRepository.save(sender);
                accountRepository.save(receiver);

                // Sender transaction
                saveTransaction(
                                sender,
                                "TRANSFER_DEBIT",
                                request.amount());

                // Receiver transaction
                saveTransaction(
                                receiver,
                                "TRANSFER_CREDIT",
                                request.amount());
        }

        public List<Transaction> getTransactions(Long accountId) {
                return transactionRepository.findByAccount_IdOrderByTransactionDateDesc(
                                accountId);
        }

        //
        private Account getActiveAccount(Long id) {

                Account account = accountRepository
                                .findById(id)
                                .orElseThrow(() -> new RuntimeException(
                                                "Account not found"));

                if (!"ACTIVE".equals(account.getStatus())) {

                        throw new RuntimeException(
                                        "Account is not active");
                }

                return account;
        }

        private Transaction saveTransaction(
                        Account account,
                        String type,
                        BigDecimal amount) {

                Transaction transaction = new Transaction();

                transaction.setAccount(account);
                transaction.setTransactionType(type);
                transaction.setAmount(amount);
                transaction.setTransactionDate(
                                LocalDateTime.now());
                transaction.setReferenceNumber(
                                "TXN-" + UUID.randomUUID());
                transaction.setStatus("SUCCESS");

                return transactionRepository.save(transaction);
        }
}
