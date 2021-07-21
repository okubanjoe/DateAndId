package com.ornate.customerservice.service;

import com.ornate.customerservice.model.Transaction;
import com.ornate.customerservice.model.TransactionGoal;
import com.ornate.customerservice.model.dto.TransactionDto;
import com.ornate.customerservice.model.dto.TransactionRequestDo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransactionService {

    Transaction createTransaction(TransactionDto transactionDto) throws Exception;
    
    Transaction updateTransaction(Long id , TransactionDto transactionDto) throws Exception;

    Transaction retrieveTransactionById(Long id);

    void deleteTransaction(Long id);

    List<Transaction> retrieveAllTransactions();

    List<Transaction> retrieveTransactionByGoalId(Long goalId) throws Exception;

    List<Transaction> getAllTransactions(List<TransactionGoal> transactionGoals) throws Exception;
}
