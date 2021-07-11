package com.ornate.customerservice.repositories;

import com.ornate.customerservice.model.Customer;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.time.Instant;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    @Query("select c from customer c where c.createdDate between :startDate and :endDate order by c.createdDate")
    Page<Customer> findByDateRange(@Param("startDate") Instant startDate,
                                   @Param("endDate")Instant endDate,
                                   Pageable pageable);

    @Query("select c from customer c where " +
            "(c.createdDate between :startDate and :endDate) and c.id=:id  order by c.createdDate")
    Page<Customer> findByDateRangeAndId(@Param("startDate") Instant startDate,
                                        @Param("endDate")Instant endDate,
                                        @Param("id") Long id,
                                        Pageable pageable);
}
