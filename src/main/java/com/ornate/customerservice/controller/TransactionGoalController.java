package com.ornate.customerservice.controller;

import com.ornate.customerservice.model.Transaction;
import com.ornate.customerservice.model.TransactionGoal;
import com.ornate.customerservice.model.dto.TransactionDto;
import com.ornate.customerservice.model.dto.TransactionGoalRequestDto;
import com.ornate.customerservice.service.TransactionGoalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactionGoal")
@Api(value = "transactionGoal")
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
    public ResponseEntity<TransactionGoal> createTransactionGoal(@RequestBody TransactionGoalRequestDto transactionRequestDo) throws Exception {

        return ResponseEntity.ok(transactionGoalService.createTransactionGoal(transactionRequestDo));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Goal by Id",
            notes = "delete goal  by id",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteTransactionById(@PathVariable("id") Long id ) throws Exception {
        transactionGoalService.deleteByGoalId(id);
        return ResponseEntity.noContent().build();
    }
}
