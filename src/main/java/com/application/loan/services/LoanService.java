package com.application.loan.services;

import com.application.loan.dto.LoanDTO;

import java.util.List;

public interface LoanService {
    LoanDTO addLoan(LoanDTO loandto);

    List<LoanDTO> getAllLoans();

    List<LoanDTO> getLoanByLoanId(String loanId);

    List<LoanDTO> getLoanByCustomerId(String customerId);

    List<LoanDTO> getLoanByLenderId(String lenderId);

    List<Object[]> aggregateLoansByLender();

    List<Object[]> aggregateLoansByInterest();

    List<Object[]> aggregateLoansByCustomerId();
}
