package com.ornate.customerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ornate.customerservice.model.Transaction;
import com.ornate.customerservice.model.TransactionGoal;
import com.ornate.customerservice.model.dto.TransactionDto;
import com.ornate.customerservice.repositories.TransactionGoalRepository;
import com.ornate.customerservice.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TransactionController.class)
@ContextConfiguration(classes = TransactionController.class)
@AutoConfigureMockMvc(addFilters = false)
class TransactionControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    TransactionService transactionService;

    @MockBean
    TransactionGoalRepository transactionGoalRepository;


    @Autowired
    Gson gson;

    String transactionJson;


    @BeforeEach
    void setup() {
        transactionJson = gson.toJson(transactionDto());
    }

    private Transaction transactionDto() {
        TransactionGoal transactionGoal = new TransactionGoal();
        transactionGoal.setGoalName("Goal1");
        transactionGoal.setId(1L);

        Transaction transaction = new Transaction();
        transaction.setTransactionGoal(transactionGoal);
        transaction.setCharge(100.0);
        transaction.setAmount(1000.0);
        transaction.setReference(""+System.nanoTime());
        transaction.setCreatedDate(Instant.now());
        transaction.setCreatedBy("System");

        return transaction;
    }


    @Test
    @DisplayName("POST `/transactions`")
    void addTransactions() throws Exception {
        mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionJson))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    @DisplayName("PUT `/transactions/{id}`")
    void updateTransactions() throws Exception {
        Long transactionId = 1L;

        TransactionGoal transactionGoal = new TransactionGoal();
        transactionGoal.setGoalName("Goal1");
        transactionGoal.setId(1L);

        Transaction transaction = new Transaction();
        transaction.setTransactionGoal(transactionGoal);
        transaction.setCharge(100.0);
        transaction.setAmount(1000.0);
        transaction.setReference(""+System.nanoTime());
        transaction.setCreatedDate(Instant.now());
        transaction.setCreatedBy("System");
        transaction.setId(transactionId);


        when(transactionService.retrieveTransactionById(transactionId)).thenReturn(transaction);
        when(transactionGoalRepository.findById(transactionGoal.getId())).thenReturn(Optional.of(transactionGoal));

        Transaction updatedTransactionDto = updateTransactionDto();

        TransactionDto transactionDto = TransactionDto.builder()
                .reference(updatedTransactionDto.getReference())
                .goalId(transactionGoal.getId())
                .charge(updatedTransactionDto.getCharge())
                .amount(updatedTransactionDto.getAmount())
                .build();

        String updatedJson = gson.toJson(transactionDto);

        when(transactionService.updateTransaction(transactionId, transactionDto)).thenReturn(updateTransactionDto());

        mockMvc.perform(put("/transactions/"+ transactionId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedJson))
                .andExpect(status().isOk()).andDo(print());
    }

    private Transaction updateTransactionDto() {
        Transaction createdTransaction = transactionDto();

        Transaction transaction = new Transaction();
        transaction.setTransactionGoal(createdTransaction.getTransactionGoal());
        transaction.setCharge(400.0);
        transaction.setAmount(3000.0);
        transaction.setReference(""+System.nanoTime());
        transaction.setUpdatedBy("System");
        transaction.setUpdatedDate(Instant.now());
        transaction.setCreatedDate(transaction.getCreatedDate());
        transaction.setCreatedBy(transaction.getCreatedBy());
        transaction.setId(1L);

        return transaction;
    }

    @Test
    @DisplayName("GET `/transactions/{transactionId}`")
    void getTransactionById() throws Exception {
        Long transactionId = 1L;

        TransactionGoal transactionGoal = new TransactionGoal();
        transactionGoal.setGoalName("Goal1");
        transactionGoal.setId(1L);

        Transaction transaction = new Transaction();
        transaction.setTransactionGoal(transactionGoal);
        transaction.setCharge(100.0);
        transaction.setAmount(1000.0);
        transaction.setReference(""+System.nanoTime());
        transaction.setCreatedDate(Instant.now());
        transaction.setCreatedBy("System");
        transaction.setId(transactionId);

        when(transactionService.retrieveTransactionById(transactionId)).thenReturn(transaction);

        mockMvc.perform(get("/transactions/"+ transactionId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    @DisplayName("GET `/transactions/all`")
    void testGetAllTransactions() throws Exception {
        Long transactionId = 1L;

        TransactionGoal transactionGoal = new TransactionGoal();
        transactionGoal.setGoalName("Goal1");
        transactionGoal.setId(1L);

        Transaction transaction = new Transaction();
        transaction.setTransactionGoal(transactionGoal);
        transaction.setCharge(100.0);
        transaction.setAmount(1000.0);
        transaction.setReference(""+System.nanoTime());
        transaction.setCreatedDate(Instant.now());
        transaction.setCreatedBy("System");
        transaction.setId(transactionId);

        Transaction transaction2 = new Transaction();
        transaction2.setTransactionGoal(transactionGoal);
        transaction2.setCharge(10.0);
        transaction2.setAmount(1200.0);
        transaction2.setReference(""+System.nanoTime());
        transaction2.setCreatedDate(Instant.now());
        transaction2.setCreatedBy("System");
        transaction2.setId(transactionId);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        transactions.add(transaction2);

        when(transactionService.retrieveAllTransactions()).thenReturn(transactions);

        mockMvc.perform(get("/transactions/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void getTransactionsByGoalId() throws Exception {
        Long transactionId = 1L;

        TransactionGoal transactionGoal = new TransactionGoal();
        transactionGoal.setGoalName("Goal1");
        transactionGoal.setId(1L);

        Transaction transaction = new Transaction();
        transaction.setTransactionGoal(transactionGoal);
        transaction.setCharge(100.0);
        transaction.setAmount(1000.0);
        transaction.setReference(""+System.nanoTime());
        transaction.setCreatedDate(Instant.now());
        transaction.setCreatedBy("System");
        transaction.setId(transactionId);

        Transaction transaction2 = new Transaction();
        transaction2.setTransactionGoal(transactionGoal);
        transaction2.setCharge(10.0);
        transaction2.setAmount(1200.0);
        transaction2.setReference(""+System.nanoTime());
        transaction2.setCreatedDate(Instant.now());
        transaction2.setCreatedBy("System");
        transaction2.setId(transactionId);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        transactions.add(transaction2);

        when(transactionService.retrieveTransactionByGoalId(transactionGoal.getId())).thenReturn(transactions);

        mockMvc.perform(get("/transactions/transaction/goalId/"+ transactionGoal.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void deleteTransactionById() throws Exception {
        Long transactionId = 1L;

        TransactionGoal transactionGoal = new TransactionGoal();
        transactionGoal.setGoalName("Goal1");
        transactionGoal.setId(1L);

        Transaction transaction = new Transaction();
        transaction.setTransactionGoal(transactionGoal);
        transaction.setCharge(100.0);
        transaction.setAmount(1000.0);
        transaction.setReference(""+System.nanoTime());
        transaction.setCreatedDate(Instant.now());
        transaction.setCreatedBy("System");
        transaction.setId(transactionId);

        when(transactionService.retrieveTransactionById(transactionId)).thenReturn(transaction);;

        mockMvc.perform(delete("/transactions/"+ transactionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}