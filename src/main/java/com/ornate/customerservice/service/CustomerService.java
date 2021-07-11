package com.ornate.customerservice.service;

import com.ornate.customerservice.model.Customer;
import com.ornate.customerservice.model.dto.CustomerRequestDo;
import org.springframework.data.domain.Page;

public interface CustomerService {

    Page<Customer> retrieveAllCustomers(CustomerRequestDo customerRequestDo);
}
