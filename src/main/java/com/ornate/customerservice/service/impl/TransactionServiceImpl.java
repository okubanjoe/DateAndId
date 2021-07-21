package com.ornate.customerservice.service.impl;

import com.ornate.customerservice.model.Transaction;
import com.ornate.customerservice.model.TransactionGoal;
import com.ornate.customerservice.model.dto.TransactionDto;
import com.ornate.customerservice.repositories.TransactionRepository;
import com.ornate.customerservice.service.TransactionGoalService;
import com.ornate.customerservice.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionGoalService transactionGoalService;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  TransactionGoalService transactionGoalService) {
        this.transactionRepository = transactionRepository;
        this.transactionGoalService = transactionGoalService;
    }

    @Override
    public Transaction createTransaction(TransactionDto transactionDto) throws Exception {
        log.info("********* about to create transaction ********");

        TransactionGoal transactionGoal = transactionGoalService
                .getTransactionGoalById(transactionDto.getGoalId());

        Transaction transaction = new Transaction();
        transaction.setTransactionGoal(transactionGoal);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setCharge(transactionDto.getCharge());
        transactionDto.setReference(transactionDto.getReference());

        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction updateTransaction(Long id, TransactionDto transactionDto) throws Exception {
        log.info("about to update transaction with id {} ", id);
        Transaction foundTransaction = retrieveTransactionById(id);
        TransactionGoal transactionGoal = transactionGoalService
                .getTransactionGoalById(transactionDto.getGoalId());
        foundTransaction.setTransactionGoal(transactionGoal);
        foundTransaction.setCharge(transactionDto.getCharge());
        foundTransaction.setAmount(transactionDto.getAmount());
        return transactionRepository.save(foundTransaction);
    }

    @Override
    public Transaction retrieveTransactionById(Long id) {
        log.info("about to find transaction by id {} ", id);
        //TODO: You can throw back an exception here
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteTransaction(Long id) {
        log.info("about to delete transaction by id {} ", id);
        Transaction transaction = retrieveTransactionById(id);
        transactionRepository.delete(transaction);
    }

    @Override
    public List<Transaction> retrieveAllTransactions() throws Exception {
        List<Transaction> availableTransactions = new ArrayList<>();

        List<TransactionGoal> transactionGoal = transactionGoalService.getallTgetTransactionGoal();

        if (transactionGoal.size() > 0) {
            for (TransactionGoal tGoals : transactionGoal) {
                List<Transaction> transFoarGoal = retrieveTransactionByGoalId(tGoals.getId());
                availableTransactions.addAll(transFoarGoal);

            }
        }
            return availableTransactions;
    }
    @Override
    public List<Transaction> retrieveTransactionByGoalId(Long goalId) throws Exception {
        TransactionGoal transactionGoal = transactionGoalService
                .getTransactionGoalById(goalId);

        return transactionRepository.findByTransactionGoalIdOrderByCreatedDate(goalId);
    }
}
