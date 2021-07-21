package com.ornate.customerservice.service.impl;

import com.ornate.customerservice.model.TransactionGoal;
import com.ornate.customerservice.repositories.TransactionGoalRepository;
import com.ornate.customerservice.service.TransactionGoalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TransactionGoalServiceImpl implements TransactionGoalService {

    private final TransactionGoalRepository transactionGoalRepository;

    public TransactionGoalServiceImpl(TransactionGoalRepository transactionGoalRepository) {
        this.transactionGoalRepository = transactionGoalRepository;
    }

    @Override
    public TransactionGoal createTransactionGoal(String goalName) {
        log.info("about to create a transaction goal {} ", goalName);
        if(StringUtils.isBlank(goalName)) {
            throw new IllegalArgumentException("Goal Name must be provided");
        }

        TransactionGoal transactionGoal = new TransactionGoal();
        transactionGoal.setGoalName(goalName);
        return transactionGoalRepository.save(transactionGoal);
    }

    @Override
    public TransactionGoal getTransactionGoalById(Long id)  {
        return transactionGoalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Goal not found"));
    }

    @Override
    public List<TransactionGoal> getallTgetTransactionGoal() throws Exception {
        return transactionGoalRepository.findAll();
    }
}
