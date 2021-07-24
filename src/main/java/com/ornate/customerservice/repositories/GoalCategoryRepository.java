package com.ornate.customerservice.repositories;

import com.ornate.customerservice.model.GoalCategory;
import com.ornate.customerservice.model.TransactionGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface GoalCategoryRepository extends JpaRepository<GoalCategory, Long>, JpaSpecificationExecutor<GoalCategory> {
    Optional<GoalCategory> findByCategoryName(String categoryName);
}
