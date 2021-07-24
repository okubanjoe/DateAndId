package com.ornate.customerservice.controller;

import com.ornate.customerservice.model.GoalCategory;
import com.ornate.customerservice.model.Transaction;
import com.ornate.customerservice.model.dto.GoalCategoryDto;
import com.ornate.customerservice.model.dto.TransactionDto;
import com.ornate.customerservice.service.GoalCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@Api(value = "category")
@Slf4j

public class GoalCategoryController {

    private final GoalCategoryService goalCategoryService;

    public GoalCategoryController(GoalCategoryService goalCategoryService) {
        this.goalCategoryService = goalCategoryService;
    }

    @PostMapping
    @ApiOperation(value = "create Goal Category",
            notes = "create category goal",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = GoalCategory.class)
    public ResponseEntity<GoalCategory> addCategoryGoal(@RequestBody GoalCategoryDto goalCategoryDto) throws Exception {

        return ResponseEntity.ok(goalCategoryService.createGoalCategory(goalCategoryDto));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update Goal Category",
            notes = "update Goal Category ",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = GoalCategory.class)
    public ResponseEntity<GoalCategory> updateCategoryGoal(@PathVariable("id") Long id ,
                                                          @RequestBody GoalCategoryDto goalCategoryDto) throws Exception {

        return ResponseEntity.ok(goalCategoryService.updateGoalCategory(id, goalCategoryDto));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find Goal Catgory ",
            notes = "Find Goal Catgory",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = GoalCategory.class)
    public ResponseEntity<GoalCategory> getGoalCategory(@PathVariable("id") Long id ) throws Exception {

        return ResponseEntity.ok(goalCategoryService.retrieveGoalCategory(id));
    }


    @GetMapping("/all")
    @ApiOperation(value = "Find All Categories",
            notes = "find all goal categories",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = GoalCategory.class)
    public ResponseEntity<List<GoalCategory>> getAllGoalCategories() throws Exception {

        return ResponseEntity.ok(goalCategoryService.retrieveAllCategories());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Category by Id",
            notes = "delete goal category by id",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteTransactionById(@PathVariable("id") Long id ) {
        goalCategoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
