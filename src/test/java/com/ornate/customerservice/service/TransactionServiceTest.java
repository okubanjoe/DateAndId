package com.ornate.customerservice.service;

import com.ornate.customerservice.BaseTestClass;
import com.ornate.customerservice.model.Transaction;
import com.ornate.customerservice.model.TransactionGoal;
import com.ornate.customerservice.model.dto.TransactionDto;
import com.ornate.customerservice.repositories.TransactionRepository;
import com.ornate.customerservice.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class TransactionServiceTest extends BaseTestClass {


    @Mock
    TransactionRepository transactionRepository;
    @Mock TransactionGoalService transactionGoalService;

    TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionServiceImpl(transactionRepository, transactionGoalService);
    }

    @Test
    void createTransaction() throws Exception {

        TransactionGoal transactionGoal = new TransactionGoal();
        transactionGoal.setGoalName("Goal1");
        transactionGoal.setId(1L);

        Transaction transaction = new Transaction();
        transaction.setAmount(1000.0);
        transaction.setCharge(100.0);
        transaction.setReference(UUID.randomUUID().toString());
        transaction.setCreatedBy("user@gmail.com");
        transaction.setCreatedDate(Instant.now());
        transaction.setTransactionGoal(transactionGoal);
        transaction.setId(1L);

        when(transactionGoalService.getTransactionGoalById(transactionGoal.getId())).thenReturn(transactionGoal);
        when(transactionRepository.save(any())).thenReturn(transaction);

        Transaction createdTransaction = transactionService.createTransaction(TransactionDto.builder()
                .amount(transaction.getAmount())
                .charge(transaction.getCharge())
                .goalId(transactionGoal.getId())
                .reference(transaction.getReference())
                .build());

        assertThat(createdTransaction).isNotNull();
        assertThat(createdTransaction.getReference()).isEqualTo(transaction.getReference());
    }


    @Test
    void retrieveTransactionById() {
        TransactionGoal transactionGoal = new TransactionGoal();
        transactionGoal.setGoalName("Goal1");
        transactionGoal.setId(1L);

        Transaction transaction = new Transaction();
        transaction.setAmount(1000.0);
        transaction.setCharge(100.0);
        transaction.setReference(UUID.randomUUID().toString());
        transaction.setCreatedBy("user@gmail.com");
        transaction.setCreatedDate(Instant.now());
        transaction.setTransactionGoal(transactionGoal);
        transaction.setId(1L);

        when(transactionRepository.findById(anyLong())).thenReturn(Optional.of(transaction));

        Transaction foundTransaction = transactionService.retrieveTransactionById(transaction.getId());

        assertThat(foundTransaction).isNotNull();
        assertThat(foundTransaction.getReference()).isEqualTo(transaction.getReference());
    }


    @Test
    void retrieveAllTransactions() {
        TransactionGoal transactionGoal = new TransactionGoal();
        transactionGoal.setGoalName("Goal1");
        transactionGoal.setId(1L);

        Transaction transaction = new Transaction();
        transaction.setAmount(1000.0);
        transaction.setCharge(100.0);
        transaction.setReference(UUID.randomUUID().toString());
        transaction.setCreatedBy("user@gmail.com");
        transaction.setCreatedDate(Instant.now());
        transaction.setTransactionGoal(transactionGoal);
        transaction.setId(1L);

        Transaction transaction2 = new Transaction();
        transaction2.setAmount(2000.0);
        transaction2.setCharge(100.0);
        transaction2.setReference(UUID.randomUUID().toString());
        transaction2.setCreatedBy("user@gmail.com");
        transaction2.setCreatedDate(Instant.now());
        transaction2.setTransactionGoal(transactionGoal);
        transaction2.setId(2L);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        transactions.add(transaction2);

        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> foundTransactions = transactionService.retrieveAllTransactions();

        assertThat(foundTransactions.size()).isPositive();
        assertThat(foundTransactions.size()).isEqualTo(2);

    }

}