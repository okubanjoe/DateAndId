package com.ornate.customerservice.repositories;

import com.ornate.customerservice.CustomerServiceApplication;
import com.ornate.customerservice.model.Transaction;
import com.ornate.customerservice.model.TransactionGoal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class TransactionRepositoryTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionGoalRepository transactionGoalRepository;

    @Test
    void findByDateRange() {
    }

    @Test
    void findByDateRangeAndId() {
    }

    @Test
    void findAllTransactionByGoal() {
    }

    @Test
    void saveTransaction() {

        Optional<TransactionGoal> transactionGoal = transactionGoalRepository.findById(1L);

        Transaction transaction = new Transaction();
        transaction.setAmount(1000.0);
        transaction.setCharge(100.0);
        transaction.setReference(""+System.nanoTime());
        transaction.setTransactionGoal(transactionGoal.get());

        Transaction transaction1 = transactionRepository.save(transaction);
        System.out.println(transaction1);

        assertThat(transaction1).isNotNull();
        //assertThat(!queryResult.isEmpty()).isTrue();
    }


}