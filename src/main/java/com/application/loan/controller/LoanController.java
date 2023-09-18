package com.application.loan.controller;

import com.application.loan.dto.LoanDTO;
import com.application.loan.exception.ResourceNotFoundException;
import com.application.loan.model.Loan;
import com.application.loan.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;


    @GetMapping
    public List<LoanDTO> getAllLoans() {
        return loanService.getAllLoans();
    }

    @PostMapping("/add")
    public LoanDTO addLoan(@RequestBody LoanDTO loanDTO) {
        LocalDate paymentDate = loanDTO.getPaymentDate();
        LocalDate dueDate = loanDTO.getDueDate();

        // Checking is payment date greater than due date
        if (paymentDate != null && dueDate != null && paymentDate.isAfter(dueDate)) {
            throw new IllegalArgumentException("Payment date cannot be greater than the due date. Loan rejected.");
        }

        return loanService.addLoan(loanDTO);
    }

    @GetMapping("/{loanId}")
    public List<LoanDTO> getLoanByLoanId(@PathVariable String loanId) {
        List<LoanDTO> loandto = loanService.getLoanByLoanId(loanId);
        if (loandto.isEmpty()) throw new ResourceNotFoundException("Loan with LoanId " + loanId + " does not exist.");
        return loandto;
    }

    @GetMapping("/customers/{customerId}")
    public List<LoanDTO> getLoanByCustomerId(@PathVariable String customerId) {
        List<LoanDTO> loandto = loanService.getLoanByCustomerId(customerId);
        if (loandto.isEmpty()) throw new ResourceNotFoundException("Loan with CustomerId " + customerId + " does not exist.");
        return loandto;
    }

    @GetMapping("/lenders/{lenderId}")
    public List<LoanDTO> getLoanByLenderId(@PathVariable String lenderId) {
        List<LoanDTO> loandto = loanService.getLoanByLenderId(lenderId);
        if (loandto.isEmpty()) throw new ResourceNotFoundException("Loan with LenderId " + lenderId + " does not exist.");
        return loandto;
    }

    @GetMapping("/aggregate/lender")
    public List<Object[]> getAggregateLoansByLender() {
        List<Object[]> result = loanService.aggregateLoansByLender();
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("No aggregate loans by lender found.");
        }
        return result;
    }

    @GetMapping("/aggregate/customer")
    public  List<Object[]> getAggregateLoansByCustomerId() {
        List<Object[]> result = loanService.aggregateLoansByCustomerId();
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("No aggregate loans by CustomerId found.");
        }
        return result;
    }

    @GetMapping("/aggregate/interest")
    public  List<Object[]> getAggregateLoansByInterest() {
        List<Object[]> result = loanService.aggregateLoansByInterest();
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("No aggregate loans by Interest found.");
        }
        return result;
    }
}





