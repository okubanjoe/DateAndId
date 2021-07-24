package com.ornate.customerservice.service.impl;

import com.ornate.customerservice.exception.ModelAlreadyExistException;
import com.ornate.customerservice.exception.ModelNotFoundException;
import com.ornate.customerservice.model.GoalCategory;
import com.ornate.customerservice.model.Transaction;
import com.ornate.customerservice.model.TransactionGoal;
import com.ornate.customerservice.model.dto.GoalCategoryDto;
import com.ornate.customerservice.repositories.GoalCategoryRepository;
import com.ornate.customerservice.service.GoalCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class GoalCategoryServiceImpl implements GoalCategoryService {

    private final GoalCategoryRepository goalCategoryRepository;

    public GoalCategoryServiceImpl(GoalCategoryRepository goalCategoryRepository) {
        this.goalCategoryRepository = goalCategoryRepository;
    }

    @Override
    public GoalCategory createGoalCategory(GoalCategoryDto goalCategoryDto) {
        goalCategoryRepository.findByCategoryName(goalCategoryDto.getCategoryName())
                .ifPresent(foundCategory ->{
            throw new ModelAlreadyExistException("category already exist");
        });

        GoalCategory goalCategory = new GoalCategory();
        goalCategory.setCategoryName(goalCategoryDto.getCategoryName());
        goalCategory.setDescription(goalCategoryDto.getDescription());

        return goalCategoryRepository.save(goalCategory);

    }

    @Override
    public GoalCategory retrieveGoalCategory(Long categoryId) {
        return goalCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ModelNotFoundException("category does not exist"));
    }

    @Override
    public GoalCategory updateGoalCategory(Long categoryId, GoalCategoryDto goalCategoryDto) {
        GoalCategory foundCategory = retrieveGoalCategory(categoryId);
        foundCategory.setDescription(goalCategoryDto.getDescription());
        foundCategory.setCategoryName(goalCategoryDto.getCategoryName());
        GoalCategory savedCategory =  goalCategoryRepository.save(foundCategory);

        return savedCategory;
    }

    @Override
    public List<GoalCategory> retrieveAllCategories() {
        return goalCategoryRepository.findAll();
    }

    @Override
    public void deleteCategory(Long categoryId) {
        GoalCategory foundCategory = retrieveGoalCategory(categoryId);
        goalCategoryRepository.delete(foundCategory);
    }
}
