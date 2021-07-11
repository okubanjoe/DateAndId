package com.ornate.customerservice.model.dto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

public interface PageableRequest {
    int getPage();
    int getPageSize();

    default Pageable buildPageRequest() {
        return PageRequest.of(getPage(), getPageSize());
    }
}
