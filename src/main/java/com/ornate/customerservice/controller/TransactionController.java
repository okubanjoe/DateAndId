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

@RestController
@RequestMapping("transactions")
@Api(value = "transactions")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    @ApiOperation(value = "get all customers in paginated format",
            notes = "date format should be this (YYYY-MM-DDTHH:mm:ss.SSSZ)",
            produces = MediaType.APPLICATION_JSON_VALUE,
            responseReference = "Page<Transaction>")
    public ResponseEntity<Page<Transaction>> getAllTransactions(TransactionRequestDo transactionRequestDo) {

        return ResponseEntity.ok(transactionService.retrieveAllTransactions(transactionRequestDo));
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

    @DeleteMapping("/{id}")
    @ApiOperation(value = "find by id  transactions",
            notes = "find by id",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteTransactionById(@PathVariable("id") Long id ) {
        transactionService.deleteTransaction(id);
    }


}
