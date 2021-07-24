package com.ornate.customerservice.controller;

import com.google.gson.Gson;
import com.ornate.customerservice.model.GoalCategory;
import com.ornate.customerservice.model.Transaction;
import com.ornate.customerservice.model.TransactionGoal;
import com.ornate.customerservice.model.dto.GoalCategoryDto;
import com.ornate.customerservice.service.GoalCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = GoalCategoryController.class)
@ContextConfiguration(classes = GoalCategoryController.class)
@AutoConfigureMockMvc(addFilters = false)
class GoalCategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GoalCategoryService goalCategoryService;


    @Autowired
    Gson gson;

    String goalCategoryJson;

    String updateGoalCategoryJson;

    @BeforeEach
    void setUp() {
        goalCategoryJson = gson.toJson(goalCategoryDto());
        updateGoalCategoryJson = gson.toJson(updatedGoalCategoryDto());
    }

    private GoalCategoryDto updatedGoalCategoryDto() {
        return GoalCategoryDto.builder()
                .description("Vacation desc")
                .categoryName("Vacation Service")
                .build();
    }

    private GoalCategoryDto goalCategoryDto() {
        return GoalCategoryDto.builder()
                .description("Home Service desc")
                .categoryName("Home Service")
                .build();
    }

    @Test
    @DisplayName("POST `/category`")
    void addCategoryGoal() throws Exception {
        GoalCategoryDto goalCategoryDto = goalCategoryDto();
        GoalCategory goalCategory = new GoalCategory();
        goalCategory.setCategoryName(goalCategoryDto.getCategoryName());
        goalCategory.setDescription(goalCategoryDto.getDescription());
        goalCategory.setId(1L);

        when(goalCategoryService.createGoalCategory(goalCategoryDto)).thenReturn(goalCategory);

        mockMvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(goalCategoryJson))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void updateCategoryGoal() throws Exception {
        GoalCategoryDto goalCategoryDto = updatedGoalCategoryDto();

        GoalCategory updateGoalCategory = new GoalCategory();
        updateGoalCategory.setCategoryName(goalCategoryDto.getCategoryName());
        updateGoalCategory.setDescription(goalCategoryDto.getDescription());
        updateGoalCategory.setId(1L);

        when(goalCategoryService.updateGoalCategory(1L, goalCategoryDto)).thenReturn(updateGoalCategory);

        mockMvc.perform(put("/category/"+ 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateGoalCategoryJson))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    @DisplayName("GET `/category/{id}`")
    void getGoalCategory() throws Exception {
        GoalCategory goalCategory = new GoalCategory();

        goalCategory.setId(1L);
        goalCategory.setCategoryName(goalCategoryDto().getCategoryName());
        goalCategory.setDescription(goalCategoryDto().getDescription());

        when(goalCategoryService.retrieveGoalCategory(goalCategory.getId())).thenReturn(goalCategory);

        mockMvc.perform(get("/category/"+ 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());

    }

    @Test
    @DisplayName("GET `/category/all`")
    void getAllGoalCategories() throws Exception {
        GoalCategory goalCategory = new GoalCategory();

        goalCategory.setId(1L);
        goalCategory.setCategoryName(goalCategoryDto().getCategoryName());
        goalCategory.setDescription(goalCategoryDto().getDescription());

        List<GoalCategory> goalCategories = new ArrayList<>();
        goalCategories.add(goalCategory);

        when(goalCategoryService.retrieveAllCategories()).thenReturn(goalCategories);

        mockMvc.perform(get("/category/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    @DisplayName("DELETE `/category/{id}`")
    void deleteTransactionById() throws Exception {

        Long categoryId = 1L;

        doNothing().when(goalCategoryService).deleteCategory(categoryId);

        mockMvc.perform(delete("/category/"+ categoryId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}