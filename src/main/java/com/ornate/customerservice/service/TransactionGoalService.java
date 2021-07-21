package com.ornate.customerservice.service;

import com.ornate.customerservice.model.TransactionGoal;

import java.util.List;

public interface TransactionGoalService {
    TransactionGoal createTransactionGoal(String goalName);
    TransactionGoal getTransactionGoalById(Long id) throws Exception;
    List<TransactionGoal> getallTgetTransactionGoal() throws  Exception;
 }
