package com.ornate.customerservice.service;

import com.ornate.customerservice.model.TransactionGoal;
import com.ornate.customerservice.model.dto.TransactionGoalRequestDto;

import java.util.List;

public interface TransactionGoalService {
    TransactionGoal createTransactionGoal(String goalName);
    TransactionGoal createTransactionGoal(TransactionGoal transactionGoal);
    TransactionGoal createTransactionGoal(TransactionGoalRequestDto transactionGoalRequestDto);
    TransactionGoal getTransactionGoalById(Long id) throws Exception;
    List<TransactionGoal> getAllTransactionGoals() throws  Exception;
 }
