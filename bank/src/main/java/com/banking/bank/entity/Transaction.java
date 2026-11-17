package com.banking.bank.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionType;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    @Column(unique = true)
    private String referenceNumber;
    private String status;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
