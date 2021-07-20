package com.ornate.customerservice.service.impl;

import com.ornate.customerservice.model.Transaction;
import com.ornate.customerservice.model.TransactionGoal;
import com.ornate.customerservice.model.dto.TransactionDto;
import com.ornate.customerservice.model.dto.TransactionRequestDo;
import com.ornate.customerservice.model.dto.TransactionSearchTerm;
import com.ornate.customerservice.repositories.TransactionRepository;
import com.ornate.customerservice.service.TransactionGoalService;
import com.ornate.customerservice.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

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
    public List<Transaction> retrieveAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> retrieveTransactionByGoalId(Long goalId) throws Exception {
        TransactionGoal transactionGoal = transactionGoalService
                .getTransactionGoalById(goalId);

        return transactionRepository.findByTransactionGoalIdOrderByCreatedDate(transactionGoal);
    }
}
