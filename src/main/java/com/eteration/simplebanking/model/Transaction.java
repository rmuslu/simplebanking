package com.eteration.simplebanking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@Table(name = "transactions")
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount", nullable = false)
    protected double amount;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "account_number", referencedColumnName = "account_number", nullable = false)
    @JsonBackReference
    private Account account;

    @Embedded
    protected TransactionStatus status;
    public Transaction(double amount) {
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    public abstract TransactionStatus apply(Account account);
}
