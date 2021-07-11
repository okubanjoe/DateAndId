package com.ornate.customerservice.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

import java.time.Instant;

@Data
@Builder
public class CustomerRequestDo implements PageableRequest {

    private int page;
    private int pageSize;
    private CustomerSearchTerm customerSearchTerm;
    private Instant startDate;
    private Instant endDate;
    private Long id;

}
