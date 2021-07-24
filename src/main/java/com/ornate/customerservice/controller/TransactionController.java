package com.ornate.customerservice.controller;


import com.ornate.customerservice.model.Transaction;
import com.ornate.customerservice.model.dto.TransactionDto;
import com.ornate.customerservice.model.dto.TransactionRequestDo;
import com.ornate.customerservice.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transactions")
@Api(value = "transactions")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping
    @ApiOperation(value = "create transactions",
            notes = "create user ",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Transaction.class)
    public ResponseEntity<Transaction> addTransactions(@RequestBody TransactionDto transactionRequestDo) throws Exception {

        return ResponseEntity.ok(transactionService.createTransaction(transactionRequestDo));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update transactions",
            notes = "update user ",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Transaction.class)
    public ResponseEntity<Transaction> updateTransactions(@PathVariable("id") Long id ,
                                                          @RequestBody TransactionDto transactionRequestDo) throws Exception {

        return ResponseEntity.ok(transactionService.updateTransaction(id, transactionRequestDo));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "find by id  transactions",
            notes = "find by id",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Transaction.class)
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") Long id ) throws Exception {

        return ResponseEntity.ok(transactionService.retrieveTransactionById(id));
    }


    @GetMapping("/all")
    @ApiOperation(value = "find by id  transactions",
            notes = "find by id",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Transaction.class)
    public ResponseEntity<List<Transaction>> getAllTransactions() throws Exception {

        return ResponseEntity.ok(transactionService.retrieveAllTransactions());
    }

    @GetMapping("/transaction/goalId/{goalId}")
    @ApiOperation(value = "find by id  transactions",
            notes = "find by id",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Transaction.class)
    public ResponseEntity<List<Transaction>> getTransactionsByGoalId(@PathVariable(required = false) Long goalId ) throws Exception {

        return ResponseEntity.ok(transactionService.retrieveTransactionByGoalId(goalId));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "find by id  transactions",
            notes = "find by id",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteTransactionById(@PathVariable("id") Long id ) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }


}
