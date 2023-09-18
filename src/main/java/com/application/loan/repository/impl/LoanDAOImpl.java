package com.application.loan.repository.impl;

import com.application.loan.repository.LoanDAO;
import com.application.loan.model.Loan;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@AllArgsConstructor
@Transactional
public class LoanDAOImpl implements LoanDAO {

    private final EntityManager entityManager;
    private final Session session;

    @Override
    public void save(Loan loan) {
        entityManager.persist(loan);
    }

    @Override
    public List<Loan> getAllLoans() {
        String queryString = "SELECT l FROM Loan l";
        TypedQuery<Loan> query = entityManager.createQuery(queryString, Loan.class);
        return query.getResultList();
    }

    @Override
    public List<Loan> getLoanByLoanId(String loanId) {
        String queryString = "SELECT l FROM Loan l WHERE l.loanId = :loanId";
        TypedQuery<Loan> query = entityManager.createQuery(queryString, Loan.class);
        query.setParameter("loanId", loanId);
        return query.getResultList();
    }

    @Override
    public List<Loan> getLoanByCustomerId(String customerId) {
        String queryString = "SELECT l FROM Loan l WHERE l.customerId = :customerId";
        TypedQuery<Loan> query = entityManager.createQuery(queryString, Loan.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    @Override
    public List<Loan> getLoanByLenderId(String lenderId) {
        String queryString = "SELECT l FROM Loan l WHERE l.lenderId = :lenderId";
        TypedQuery<Loan> query = entityManager.createQuery(queryString, Loan.class);
        query.setParameter("lenderId", lenderId);
        return query.getResultList();
    }

    public List<Object[]> getAggregateLoansByLender() {
        String queryString = "SELECT l.lenderId, SUM(l.remainingAmount), SUM(l.interestPerDay), SUM(l.penaltyPerDay) FROM Loan l GROUP BY l.lenderId";
        TypedQuery<Object[]> query = entityManager.createQuery(queryString, Object[].class);
        return query.getResultList();
    }

    public List<Object[]> getAggregateLoansByCustomerId () {
        String queryString = "SELECT l.customerId, SUM(l.remainingAmount), SUM(l.interestPerDay), SUM(l.penaltyPerDay) FROM Loan l GROUP BY l.customerId";
        TypedQuery<Object[]> query = entityManager.createQuery(queryString, Object[].class);
        return query.getResultList();
    }

    public List<Object[]> getAggregateLoansByInterest() {
        String queryString = "SELECT l.interestPerDay, SUM(l.remainingAmount), SUM(l.interestPerDay), SUM(l.penaltyPerDay) FROM Loan l GROUP BY l.interestPerDay";
        TypedQuery<Object[]> query = entityManager.createQuery(queryString, Object[].class);
        return query.getResultList();
    }

}
