package com.ornate.customerservice.controller;


import com.ornate.customerservice.model.Transaction;
import com.ornate.customerservice.model.dto.TransactionRequestDo;
import com.ornate.customerservice.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customers")
@Api(value = "customers")
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

}
