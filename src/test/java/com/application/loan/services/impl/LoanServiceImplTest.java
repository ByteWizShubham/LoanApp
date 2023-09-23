package com.application.loan.services.impl;
import com.application.loan.dto.LoanDTO;
import com.application.loan.model.Loan;
import com.application.loan.repository.impl.LoanDAOImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceImplTest {

    @InjectMocks
    private LoanServiceImpl loanService;

    @Mock
    private LoanDAOImpl loanDAO;

    @Mock
    private ModelMapper modelMapper ;

    @Test
    public void testAddLoan_Success() {

        // Create a LoanDTO
        LoanDTO loanDTO = getLoanDTO();
        when(modelMapper.map(loanDTO,Loan.class)).thenReturn(new ModelMapper().map(loanDTO,Loan.class));

        // Create a Loan entity by mapping the LoanDTO
        Loan loanEntity = modelMapper.map(loanDTO, Loan.class);
        when(modelMapper.map(loanEntity,LoanDTO.class)).thenReturn(new ModelMapper().map(loanEntity,LoanDTO.class));

        // Mock the behavior of loanDAO.save method
        doNothing().when(loanDAO).save(any(Loan.class));

        // Call the addLoan method
        LoanDTO savedLoanDTO = loanService.addLoan(loanDTO);

        // Verify the result
        assertEquals(loanDTO, savedLoanDTO);

       // Verify that loanDAO.save was called with the loanEntity
        verify(loanDAO, times(1)).save(loanEntity);
    }

    @Test
    public void testGetAllLoans_Success() {
        List<Loan> mockLoans = createMockLoanList();
        when(loanDAO.getAllLoans()).thenReturn(mockLoans);

        List<LoanDTO> expectedLoanDTOs = mockLoans.stream()
                .map(loan -> modelMapper.map(loan, LoanDTO.class))
                .collect(Collectors.toList());

        List<LoanDTO> actualLoanDTOs = loanService.getAllLoans();

        // Assert
        assertEquals(expectedLoanDTOs, actualLoanDTOs);
    }


    @Test
    public void testGetLoanByLoanId_Success() {
        // Arrange
        String loanId = "L1";
        List<Loan> mockLoanList = createMockLoanList();
        when(loanDAO.getLoanByLoanId(loanId)).thenReturn(mockLoanList);

        List<LoanDTO> expectedLoanDTOs = mockLoanList.stream()
                .map(loan -> modelMapper.map(loan, LoanDTO.class))
                .collect(Collectors.toList());

        List<LoanDTO> actualLoanDTOs = loanService.getLoanByLoanId(loanId);

        // Assert
        assertEquals(expectedLoanDTOs, actualLoanDTOs);
    }



    private LoanDTO getLoanDTO() {
        // Create a LoanDTO
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setLoanId("L1");
        loanDTO.setCustomerId("C1");
        loanDTO.setLenderId("LEN1");
        loanDTO.setAmount(BigDecimal.valueOf(10000.00));
        loanDTO.setRemainingAmount(BigDecimal.valueOf(10000.00));
        loanDTO.setPaymentDate(LocalDate.parse("2023-05-06"));
        loanDTO.setInterestPerDay(BigDecimal.valueOf(0.01));
        loanDTO.setDueDate(LocalDate.parse("2023-10-10"));
        loanDTO.setPenaltyPerDay(BigDecimal.valueOf(0.01));
        loanDTO.setCancelled(false);
        return loanDTO;
    }

    private List<Loan> createMockLoanList() {
        // Create a list of mock Loan objects for testing.
        List<Loan> mockLoans = new ArrayList<>();

        Loan loan1 = new Loan();
        loan1.setId(1L);  // Set the ID
        loan1.setLoanId("L1");
        loan1.setCustomerId("C1");
        loan1.setLenderId("LEN1");
        loan1.setAmount(BigDecimal.valueOf(10000.00));
        loan1.setRemainingAmount(BigDecimal.valueOf(8000.00));
        loan1.setPaymentDate(LocalDate.parse("2023-05-06"));
        loan1.setInterestPerDay(BigDecimal.valueOf(0.01));
        loan1.setDueDate(LocalDate.parse("2023-10-10"));
        loan1.setPenaltyPerDay(BigDecimal.valueOf(0.02));
        loan1.setCancelled(false);
        mockLoans.add(loan1);

        Loan loan2 = new Loan();
        loan2.setId(2L);  // Set the ID
        loan2.setLoanId("L2");
        loan2.setCustomerId("C2");
        loan2.setLenderId("LEN2");
        loan2.setAmount(BigDecimal.valueOf(15000.00));
        loan2.setRemainingAmount(BigDecimal.valueOf(12000.00));
        loan2.setPaymentDate(LocalDate.parse("2023-06-10"));
        loan2.setInterestPerDay(BigDecimal.valueOf(0.015));
        loan2.setDueDate(LocalDate.parse("2023-11-15"));
        loan2.setPenaltyPerDay(BigDecimal.valueOf(0.03));
        loan2.setCancelled(false);
        mockLoans.add(loan2);

        // Add more mock loans as needed.

        return mockLoans;
    }



}
