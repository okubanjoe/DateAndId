package com.ornate.customerservice.controller;

import com.ornate.customerservice.model.Transaction;
import com.ornate.customerservice.model.TransactionGoal;
import com.ornate.customerservice.model.dto.TransactionDto;
import com.ornate.customerservice.service.TransactionGoalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("goal")
@Api(value = "goal")
@Slf4j
public class TransactionGoalController {

    private final TransactionGoalService transactionGoalService;

    public TransactionGoalController(TransactionGoalService transactionGoalService) {
        this.transactionGoalService = transactionGoalService;
    }

    @PostMapping
    @ApiOperation(value = "create transaction goal",
            notes = "create transaction goal ",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = TransactionGoal.class)
    public ResponseEntity<TransactionGoal> createTransactionGoal(@RequestBody TransactionGoal transactionRequestDo) throws Exception {

        return ResponseEntity.ok(transactionGoalService.createTransactionGoal(transactionRequestDo));
    }
}
