package com.application.loan.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "loan_store")
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_id")
    private String loanId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "lender_id")
    private String lenderId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "remaining_amount")
    private BigDecimal remainingAmount;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "interest_per_day")
    private BigDecimal interestPerDay;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "penalty_per_day")
    private BigDecimal penaltyPerDay;

    @Column(name = "cancelled")
    private boolean cancelled;

}
