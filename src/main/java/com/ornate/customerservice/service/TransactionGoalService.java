package com.ornate.customerservice.service;

import com.ornate.customerservice.model.TransactionGoal;

public interface TransactionGoalService {
    TransactionGoal createTransactionGoal(String goalName);
    TransactionGoal getTransactionGoalById(Long id) throws Exception;
}
