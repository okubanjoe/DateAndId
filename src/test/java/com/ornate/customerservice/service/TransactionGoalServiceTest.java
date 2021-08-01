package com.ornate.customerservice.service;

import com.ornate.customerservice.BaseTestClass;
import com.ornate.customerservice.model.GoalCategory;
import com.ornate.customerservice.model.TransactionGoal;
import com.ornate.customerservice.model.dto.TransactionGoalRequestDto;
import com.ornate.customerservice.repositories.GoalCategoryRepository;
import com.ornate.customerservice.repositories.TransactionGoalRepository;
import com.ornate.customerservice.service.impl.GoalCategoryServiceImpl;
import com.ornate.customerservice.service.impl.TransactionGoalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class TransactionGoalServiceTest  {

    @Mock
    TransactionGoalRepository transactionGoalRepository;
    @Mock
    GoalCategoryService goalCategoryService;

    TransactionGoalService transactionGoalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionGoalService = new TransactionGoalServiceImpl(transactionGoalRepository, goalCategoryService);
    }

    @Test
    void createTransactionGoal() {
        GoalCategory goalCategory = new GoalCategory();
        goalCategory.setCategoryName("Test1");
        goalCategory.setDescription("Testing");
        goalCategory.setId(1L);

        TransactionGoal transactionGoal =   new TransactionGoal();
        transactionGoal.setGoalName("Goal");
        transactionGoal.setGoalCategory(goalCategory);
        transactionGoal.setId(1L);

        when(goalCategoryService.retrieveGoalCategory(anyLong())).thenReturn(goalCategory);
        when(transactionGoalRepository.save(any())).thenReturn(transactionGoal);

        TransactionGoal savedGoal = transactionGoalService.createTransactionGoal(transactionGoal);

        assertThat(savedGoal).isNotNull();
        assertThat(savedGoal.getId()).isEqualTo(transactionGoal.getId());
        assertThat(savedGoal.getGoalCategory()).isNotNull();
    }


    @Test
    void createTransactionGoal_1() {
        TransactionGoalRequestDto transactionGoalRequestDto = new TransactionGoalRequestDto();
        transactionGoalRequestDto.setCategoryId(1L);
        transactionGoalRequestDto.setGoalName("GOAL");

        GoalCategory goalCategory = new GoalCategory();
        goalCategory.setCategoryName("Test1");
        goalCategory.setDescription("Testing");
        goalCategory.setId(1L);

        TransactionGoal transactionGoal =   new TransactionGoal();
        transactionGoal.setGoalName("GOAL");
        transactionGoal.setGoalCategory(goalCategory);
        transactionGoal.setId(1L);


        when(goalCategoryService.retrieveGoalCategory(anyLong())).thenReturn(goalCategory);
        when(transactionGoalRepository.save(any())).thenReturn(transactionGoal);

        TransactionGoal savedGoal = transactionGoalService.createTransactionGoal(transactionGoal);

        assertThat(savedGoal).isNotNull();
        assertThat(savedGoal.getId()).isEqualTo(transactionGoal.getId());
        assertThat(savedGoal.getGoalCategory()).isNotNull();
    }

    @Test
    void getTransactionGoalById() throws Exception {
        GoalCategory goalCategory = new GoalCategory();
        goalCategory.setCategoryName("Test1");
        goalCategory.setDescription("Testing");
        goalCategory.setId(1L);

        TransactionGoal transactionGoal =   new TransactionGoal();
        transactionGoal.setGoalName("Goal");
        transactionGoal.setGoalCategory(goalCategory);
        transactionGoal.setId(1L);

        when(transactionGoalRepository.findById(anyLong())).thenReturn(Optional.of(transactionGoal));

        TransactionGoal foundGoal = transactionGoalService.getTransactionGoalById(transactionGoal.getId());

        assertThat(foundGoal).isNotNull();
        assertThat(foundGoal.getGoalCategory()).isNotNull();
    }

    @Test
    void getAllTransactionGoals() throws Exception {

        GoalCategory goalCategory = new GoalCategory();
        goalCategory.setCategoryName("Test1");
        goalCategory.setDescription("Testing");
        goalCategory.setId(1L);

        TransactionGoal transactionGoal =   new TransactionGoal();
        transactionGoal.setGoalName("Goal");
        transactionGoal.setGoalCategory(goalCategory);
        transactionGoal.setId(1L);

        TransactionGoal transactionGoal2 =   new TransactionGoal();
        transactionGoal2.setGoalName("Goal2");
        transactionGoal2.setGoalCategory(goalCategory);
        transactionGoal2.setId(2L);

        List<TransactionGoal> transactionGoals = new ArrayList<>();
        transactionGoals.add(transactionGoal);
        transactionGoals.add(transactionGoal2);

        when(transactionGoalRepository.findAll()).thenReturn(transactionGoals);

        List<TransactionGoal> goals = transactionGoalService.getAllTransactionGoals();

        assertThat(goals.isEmpty()).isFalse();
        assertThat(goals.size()).isPositive();
    }

    @Test
    void deleteGoal() {
        GoalCategory goalCategory = new GoalCategory();
        goalCategory.setCategoryName("Home Services");
        goalCategory.setDescription("Home Services Desc");
        goalCategory.setId(1L);

        TransactionGoal transactionGoal =   new TransactionGoal();
        transactionGoal.setGoalName("Goal");
        transactionGoal.setGoalCategory(goalCategory);
        transactionGoal.setId(1L);

        when(transactionGoalRepository.findById(anyLong())).thenReturn(Optional.of(transactionGoal));
        doNothing().when(transactionGoalRepository).delete(transactionGoal);

        transactionGoalService.deleteByGoalId(transactionGoal.getId());
    }
}