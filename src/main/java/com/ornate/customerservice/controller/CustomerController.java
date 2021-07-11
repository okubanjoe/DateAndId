package com.ornate.customerservice.controller;


import com.ornate.customerservice.model.Customer;
import com.ornate.customerservice.model.dto.CustomerRequestDo;
import com.ornate.customerservice.model.dto.CustomerSearchTerm;
import com.ornate.customerservice.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("customers")
@Api(value = "customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ApiOperation(value = "get all customers in paginated format",
            notes = "date format should be this (YYYY-MM-DDTHH:mm:ss.SSSZ)",
            produces = MediaType.APPLICATION_JSON_VALUE,
            responseReference = "Page<Customer>")
    public ResponseEntity<Page<Customer>> getAllWalletToBankTransactions(@RequestParam CustomerSearchTerm customerSearchTerm,
                                                                         @RequestParam Instant startDate ,
                                                                         @RequestParam Instant endDate,
                                                                         @RequestParam(required = false)  Integer page,
                                                                         @RequestParam(required = false)  Integer pageSize,
                                                                         @RequestParam(required = false)  Long id) {
        CustomerRequestDo customerRequestDo = CustomerRequestDo.builder()
                .endDate(endDate == null ? null : endDate)
                .startDate(startDate == null ? null : startDate)
                .page(page == null ? 1 : page)
                .customerSearchTerm(customerSearchTerm)
                .pageSize(pageSize == null ? 25 : pageSize)
                .id(id)
                .build();

        return ResponseEntity.ok(customerService.retrieveAllCustomers(customerRequestDo));
    }

}
