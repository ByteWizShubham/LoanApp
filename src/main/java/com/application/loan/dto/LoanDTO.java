package com.application.loan.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LoanDTO {
        private Long id;
        private String loanId;
        private String customerId;
        private String lenderId;
        private BigDecimal amount;
        private BigDecimal remainingAmount;
        private LocalDate paymentDate;
        private BigDecimal interestPerDay;
        private LocalDate dueDate;
        private BigDecimal penaltyPerDay;
        private boolean cancelled;
}
