package com.example.rewardscalculator.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "TRANSACTION")
@Getter
@Setter
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "AMOUNT")
    private double amount;

    @Transient
    private int points;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "TRANSACTION_DATE")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerEntity customer;

}
