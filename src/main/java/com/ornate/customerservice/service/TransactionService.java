package com.ornate.customerservice.service;

import com.ornate.customerservice.model.Transaction;
import com.ornate.customerservice.model.dto.TransactionRequestDo;
import org.springframework.data.domain.Page;

public interface TransactionService {

    Page<Transaction> retrieveAllTransactions(TransactionRequestDo transactionRequestDo);
}
