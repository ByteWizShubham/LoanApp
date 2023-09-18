package com.application.loan.repository;

import com.application.loan.model.Loan;

import java.util.List;

public interface LoanDAO {

    void save(Loan loan);

    List<Loan> getAllLoans();

    List<Loan> getLoanByLoanId(String loanId);

    List<Loan> getLoanByCustomerId(String customerId);

    List<Loan> getLoanByLenderId(String lenderId);

    List<Object[]> getAggregateLoansByLender();

    List<Object[]> getAggregateLoansByCustomerId ();

    List<Object[]> getAggregateLoansByInterest();
}
