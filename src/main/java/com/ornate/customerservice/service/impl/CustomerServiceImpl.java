package com.ornate.customerservice.service.impl;

import com.ornate.customerservice.model.Customer;
import com.ornate.customerservice.model.dto.CustomerRequestDo;
import com.ornate.customerservice.model.dto.CustomerSearchTerm;
import com.ornate.customerservice.model.dto.PageableRequest;
import com.ornate.customerservice.repositories.CustomerRepository;
import com.ornate.customerservice.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<Customer> retrieveAllCustomers(CustomerRequestDo customerRequestDo) {

        if (customerRequestDo.getCustomerSearchTerm() == null) {
            customerRequestDo.setCustomerSearchTerm(CustomerSearchTerm.ALL);
        }
        Page<Customer> customerDetails;

        Pageable pageable = PageRequest.of(customerRequestDo.getPage(),
                customerRequestDo.getPageSize(), Sort.Direction.DESC);

        switch (customerRequestDo.getCustomerSearchTerm().name()) {
            case "ALL":
                customerDetails = customerRepository.findAll(pageable);
                break;
            case "DATE_RANGE":
                if (customerRequestDo.getStartDate() == null) {
                    customerRequestDo.setStartDate(Instant.now());
                }

                if (customerRequestDo.getEndDate() == null) {
                    customerRequestDo.setEndDate(Instant.now().plus(3, ChronoUnit.HOURS));
                }

                customerDetails = customerRepository.findByDateRange(customerRequestDo.getStartDate(),
                        customerRequestDo.getEndDate(), pageable);
                break;

            case "DATE_RANGE_AND_ID":
                if (customerRequestDo.getStartDate() == null) {
                    customerRequestDo.setStartDate(Instant.now());
                }

                if (customerRequestDo.getEndDate() == null) {
                    customerRequestDo.setEndDate(Instant.now().plus(3, ChronoUnit.HOURS));
                }

                customerDetails = customerRepository.findByDateRangeAndId(customerRequestDo.getStartDate(),
                        customerRequestDo.getEndDate(),customerRequestDo.getId(),pageable);

            default:
                throw new IllegalArgumentException("Invalid search term");
        }
        return customerDetails;
    }
}
