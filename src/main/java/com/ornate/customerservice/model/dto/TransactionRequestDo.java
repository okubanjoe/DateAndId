package com.ornate.customerservice.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
public class TransactionRequestDo {

    private int page = 0;
    private int pageSize = 25;
    private TransactionSearchTerm transactionSearchTerm;
    private Instant startDate;
    private Instant endDate;
    private Long id;

}
