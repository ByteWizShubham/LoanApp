package com.application.loan.services.impl;

import com.application.loan.repository.LoanDAO;
import com.application.loan.dto.LoanDTO;
import com.application.loan.model.Loan;
import com.application.loan.services.LoanService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {


    @Autowired
    private LoanDAO loanDAO;

    @Autowired
    private final ModelMapper modelMapper;


    public LoanServiceImpl(LoanDAO loanDAO, ModelMapper modelMapper) {
        this.loanDAO = loanDAO;
        this.modelMapper = modelMapper;
    }

    private final Logger logger = LoggerFactory.getLogger(Loan.class);

    @Override
    public LoanDTO addLoan(LoanDTO loandto) {

        // Checking if the loan crosses the due date
        if (loandto.getDueDate() != null && LocalDate.now().isAfter(loandto.getDueDate())) logLoanCrossedDueDate(loandto);

        // Use the LoanMapper to convert LoanDTO to Loan entity
        Loan loan = modelMapper.map(loandto, Loan.class);

        //Save Loan
        loanDAO.save(loan);

        // Convert the saved Loan entity back to LoanDTO
        LoanDTO savedLoanDTO = modelMapper.map(loan, LoanDTO.class);

        return savedLoanDTO;
    }

    @Override
    public List<LoanDTO> getAllLoans() {
        List<Loan> loans =loanDAO.getAllLoans();
        List<LoanDTO> loanDTOs = loans.stream()
                .map(loan -> modelMapper.map(loan, LoanDTO.class))
                .collect(Collectors.toList());
        return loanDTOs;
    }

    @Override
    public List<LoanDTO> getLoanByLoanId(String loanId) {
        List<Loan> loanList = loanDAO.getLoanByLoanId(loanId);
        List<LoanDTO> loanDTOs = loanList.stream().map(loan -> modelMapper.map(loan, LoanDTO.class)).collect(Collectors.toList());
        return loanDTOs;
    }

    @Override
    public List<LoanDTO> getLoanByCustomerId(String customerId) {
        List<Loan> loanList = loanDAO.getLoanByCustomerId(customerId);
        List<LoanDTO> loanDTOs = loanList.stream().map(loan -> modelMapper.map(loan, LoanDTO.class)).collect(Collectors.toList());
        return  loanDTOs;
    }

    @Override
    public List<LoanDTO> getLoanByLenderId(String lenderId) {
        List<Loan> loanList = loanDAO.getLoanByLenderId(lenderId);
        List<LoanDTO> loanDTOs = loanList.stream().map(loan -> modelMapper.map(loan, LoanDTO.class)).collect(Collectors.toList());
        return loanDTOs;
    }

    @Override
    public List<Object[]> aggregateLoansByLender() {
        return loanDAO.getAggregateLoansByLender();
    }

    @Override
    public List<Object[]> aggregateLoansByInterest() {
        return loanDAO.getAggregateLoansByInterest();
    }

    @Override
    public List<Object[]> aggregateLoansByCustomerId() {
        return loanDAO.getAggregateLoansByCustomerId();
    }


    private void logLoanCrossedDueDate(LoanDTO loandto) {
        logger.warn("Loan with ID {} has crossed the due date.", loandto.getLoanId());
    }


}
