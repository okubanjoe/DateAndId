package com.ornate.customerservice.service.impl;

import com.ornate.customerservice.model.GoalCategory;
import com.ornate.customerservice.model.TransactionGoal;
import com.ornate.customerservice.model.dto.TransactionGoalRequestDto;
import com.ornate.customerservice.repositories.TransactionGoalRepository;
import com.ornate.customerservice.service.GoalCategoryService;
import com.ornate.customerservice.service.TransactionGoalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TransactionGoalServiceImpl implements TransactionGoalService {

    private final TransactionGoalRepository transactionGoalRepository;
    private final GoalCategoryService goalCategoryService;

    public TransactionGoalServiceImpl(TransactionGoalRepository transactionGoalRepository,
                                      GoalCategoryService goalCategoryService) {
        this.transactionGoalRepository = transactionGoalRepository;
        this.goalCategoryService = goalCategoryService;
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
    public TransactionGoal createTransactionGoal(TransactionGoal transactionGoal) {
        GoalCategory foundGoalCategory =
                goalCategoryService.retrieveGoalCategory(transactionGoal.getGoalCategory().getId());
        if(foundGoalCategory == null) {
            throw new IllegalArgumentException("Invalid Goal category");
        }
        TransactionGoal goal = new TransactionGoal();
        goal.setGoalCategory(foundGoalCategory);
        goal.setGoalName(transactionGoal.getGoalName());
        return transactionGoalRepository.save(goal);
    }

    @Override
    public TransactionGoal createTransactionGoal(TransactionGoalRequestDto transactionGoalRequestDto) {
        GoalCategory foundGoalCategory =
                goalCategoryService.retrieveGoalCategory(transactionGoalRequestDto.getCategoryId());

        TransactionGoal transactionGoal = new TransactionGoal();
        transactionGoal.setGoalCategory(foundGoalCategory);
        transactionGoal.setGoalName(transactionGoalRequestDto.getGoalName());

        return transactionGoalRepository.save(transactionGoal);
    }

    @Override
    public TransactionGoal getTransactionGoalById(Long id)  {
        return transactionGoalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Goal not found"));
    }

    @Override
    public List<TransactionGoal> getAllTransactionGoals() throws Exception {
        return transactionGoalRepository.findAll();
    }

    @Override
    public void deleteByGoalId(Long id) {
        TransactionGoal foundTransactionGoal = getTransactionGoalById(id);
        transactionGoalRepository.delete(foundTransactionGoal);
    }
}
