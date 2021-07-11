package com.ornate.customerservice.service.impl;

import com.ornate.customerservice.model.Transaction;
import com.ornate.customerservice.model.dto.TransactionRequestDo;
import com.ornate.customerservice.model.dto.TransactionSearchTerm;
import com.ornate.customerservice.repositories.TransactionRepository;
import com.ornate.customerservice.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Page<Transaction> retrieveAllTransactions(TransactionRequestDo transactionRequestDo) {

        if (transactionRequestDo.getTransactionSearchTerm() == null) {
            transactionRequestDo.setTransactionSearchTerm(TransactionSearchTerm.ALL);
        }
        Page<Transaction> customerDetails;

        Pageable pageable = PageRequest.of(0, 25);

        switch (transactionRequestDo.getTransactionSearchTerm().name()) {
            case "ALL":
                customerDetails = transactionRepository.findAll(pageable);
                break;
            case "DATE_RANGE":
                if (transactionRequestDo.getStartDate() == null) {
                    transactionRequestDo.setStartDate(Instant.now());
                }

                if (transactionRequestDo.getEndDate() == null) {
                    transactionRequestDo.setEndDate(Instant.now().plus(3, ChronoUnit.HOURS));
                }

                customerDetails = transactionRepository.findByDateRange(transactionRequestDo.getStartDate(),
                        transactionRequestDo.getEndDate(), pageable);
                break;

            case "DATE_RANGE_AND_ID":
                if (transactionRequestDo.getStartDate() == null) {
                    transactionRequestDo.setStartDate(Instant.now());
                }

                if (transactionRequestDo.getEndDate() == null) {
                    transactionRequestDo.setEndDate(Instant.now().plus(3, ChronoUnit.HOURS));
                }

                customerDetails = transactionRepository.findByDateRangeAndId(transactionRequestDo.getStartDate(),
                        transactionRequestDo.getEndDate(), transactionRequestDo.getId(),pageable);
                break;

            default:
                throw new IllegalArgumentException("Invalid search term");
        }
        return customerDetails;
    }
}
