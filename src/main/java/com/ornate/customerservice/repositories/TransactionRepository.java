package com.ornate.customerservice.repositories;

import com.ornate.customerservice.model.Transaction;
import com.ornate.customerservice.model.TransactionGoal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    @Query("select t from transaction t where t.createdDate between :startDate and :endDate order by t.createdDate")
    Page<Transaction> findByDateRange(@Param("startDate") Instant startDate,
                                      @Param("endDate")Instant endDate,
                                      Pageable pageable);

    @Query("select t from transaction t where " +
            "(t.createdDate between :startDate and :endDate) and t.transactionGoal.id=:id  order by t.createdDate")
    Page<Transaction> findByDateRangeAndId(@Param("startDate") Instant startDate,
                                           @Param("endDate")Instant endDate,
                                           @Param("id") Long id,
                                           Pageable pageable);

    //@Query("select t from transaction t where t.transactionGoal = ?1 order by t.createdDate desc")
    List<Transaction> findByTransactionGoalIdOrderByCreatedDate(Long id);


}
