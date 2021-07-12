package com.ornate.customerservice.service;

import com.ornate.customerservice.model.Transaction;
import com.ornate.customerservice.model.dto.TransactionDto;
import com.ornate.customerservice.model.dto.TransactionRequestDo;
import org.springframework.data.domain.Page;

public interface TransactionService {

    Page<Transaction> retrieveAllTransactions(TransactionRequestDo transactionRequestDo);

    Transaction createTransaction(TransactionDto transactionDto) throws Exception;
    
    Transaction updateTransaction(Long id , TransactionDto transactionDto) throws Exception;

    Transaction retrieveTransactionById(Long id);

    void deleteTransaction(Long id);
}
