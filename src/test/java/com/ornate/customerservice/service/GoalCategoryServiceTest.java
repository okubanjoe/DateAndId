package com.ornate.customerservice.service;

import com.ornate.customerservice.exception.ModelAlreadyExistException;
import com.ornate.customerservice.model.GoalCategory;
import com.ornate.customerservice.model.dto.GoalCategoryDto;
import com.ornate.customerservice.repositories.GoalCategoryRepository;
import com.ornate.customerservice.service.impl.GoalCategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GoalCategoryServiceTest {

    @Mock
    GoalCategoryRepository goalCategoryRepository;
    GoalCategoryService goalCategoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        goalCategoryService = new GoalCategoryServiceImpl(goalCategoryRepository);
    }

    @Test
    void createGoalCategory() {
        GoalCategoryDto goalCategoryDto = GoalCategoryDto.builder()
                .categoryName("Home")
                .description("Category Home")
                .build();

        GoalCategory goalCategory = new GoalCategory();
        goalCategory.setCategoryName(goalCategoryDto.getCategoryName());
        goalCategory.setDescription(goalCategoryDto.getDescription());
        goalCategory.setId(1L);

        when(goalCategoryRepository.save(any())).thenReturn(goalCategory);

        GoalCategory savedCategory = goalCategoryService.createGoalCategory(goalCategoryDto);

        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getId()).isEqualTo(goalCategory.getId());
    }



    @Test
    void retrieveGoalCategory() {
        Long categoryId = 1L;
        GoalCategory goalCategory = new GoalCategory();
        goalCategory.setCategoryName("Home Service");
        goalCategory.setDescription("Home Service Desc");
        goalCategory.setId(categoryId);

        when(goalCategoryRepository.findById(categoryId)).thenReturn(Optional.of(goalCategory));

        GoalCategory foundCategory = goalCategoryService.retrieveGoalCategory(categoryId);

        assertThat(foundCategory).isNotNull();
        assertThat(foundCategory.getId()).isEqualTo(categoryId);
    }

    @Test
    void updateGoalCategory() {
        GoalCategoryDto goalCategoryDto = GoalCategoryDto.builder()
                .categoryName("Vacation")
                .description("Vacation desc")
                .build();

        GoalCategory goalCategory = new GoalCategory();
        goalCategory.setCategoryName("Home Services");
        goalCategory.setDescription("Home Services Desc");
        goalCategory.setId(1L);

        GoalCategory updatedCategory = new GoalCategory();
        updatedCategory.setCategoryName(goalCategoryDto.getCategoryName());
        updatedCategory.setDescription(goalCategoryDto.getDescription());
        updatedCategory.setId(goalCategory.getId());

        when(goalCategoryRepository.findById(1L)).thenReturn(Optional.of(goalCategory));
        when(goalCategoryRepository.save(updatedCategory)).thenReturn(updatedCategory);

        GoalCategory updatedResult = goalCategoryService.updateGoalCategory(goalCategory.getId(), goalCategoryDto);

        assertThat(updatedResult).isNotNull();
        assertThat(updatedResult.getId()).isEqualTo(goalCategory.getId());
    }

    @Test
    void retrieveAllCategories() {

        GoalCategory goalCategory = new GoalCategory();
        goalCategory.setCategoryName("Home Services");
        goalCategory.setDescription("Home Services Desc");
        goalCategory.setId(1L);

        GoalCategory goalCategory2 = new GoalCategory();
        goalCategory2.setCategoryName("Vacation Services");
        goalCategory2.setDescription("Vacation Services Desc");
        goalCategory.setId(2L);

        List<GoalCategory> goalCategories = new ArrayList<>();
        goalCategories.add(goalCategory);
        goalCategories.add(goalCategory2);

        when(goalCategoryRepository.findAll()).thenReturn(goalCategories);

        List<GoalCategory> categories = goalCategoryService.retrieveAllCategories();

        assertThat(categories.isEmpty()).isFalse();
        assertThat(categories.size()).isPositive();
        assertThat(categories.get(0)).isEqualTo(goalCategory);


    }

    @Test
    void deleteCategory() {
        GoalCategory goalCategory = new GoalCategory();
        goalCategory.setCategoryName("Home Services");
        goalCategory.setDescription("Home Services Desc");
        goalCategory.setId(1L);

        when(goalCategoryRepository.findById(goalCategory.getId())).thenReturn(Optional.of(goalCategory));
        doNothing().when(goalCategoryRepository).delete(goalCategory);

        goalCategoryService.deleteCategory(goalCategory.getId());
    }
}