package com.ornate.customerservice.service;

import com.ornate.customerservice.model.GoalCategory;
import com.ornate.customerservice.model.dto.GoalCategoryDto;

import java.util.List;

public interface GoalCategoryService {

    GoalCategory createGoalCategory(GoalCategoryDto goalCategoryDto);
    GoalCategory retrieveGoalCategory(Long categoryId);
    GoalCategory updateGoalCategory(Long categoryId, GoalCategoryDto goalCategoryDto);
    List<GoalCategory> retrieveAllCategories();
    void deleteCategory(Long categoryId);
}
