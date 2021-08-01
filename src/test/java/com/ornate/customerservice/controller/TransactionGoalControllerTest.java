package com.ornate.customerservice.controller;

import com.google.gson.Gson;
import com.ornate.customerservice.model.GoalCategory;
import com.ornate.customerservice.model.TransactionGoal;
import com.ornate.customerservice.model.dto.TransactionGoalRequestDto;
import com.ornate.customerservice.service.TransactionGoalService;
import com.ornate.customerservice.service.TransactionService;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TransactionGoalController.class)
@ContextConfiguration(classes = TransactionGoalController.class)
@AutoConfigureMockMvc(addFilters = false)
class TransactionGoalControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TransactionGoalService transactionGoalService;


    @Autowired
    Gson gson;

    @BeforeEach
    void setUp() {
    }


    @Test
    @DisplayName("POST `/transactionGoal`")
    void createTransactionGoal() throws Exception {

        TransactionGoalRequestDto transactionGoalRequestDto = TransactionGoalRequestDto.builder()
                .goalName("GOAL")
                .categoryId(1L)
                .build();

        GoalCategory goalCategory = new GoalCategory();
        goalCategory.setCategoryName("Test1");
        goalCategory.setDescription("Testing");
        goalCategory.setId(1L);

        TransactionGoal transactionGoal =   new TransactionGoal();
        transactionGoal.setGoalName("GOAL");
        transactionGoal.setGoalCategory(goalCategory);
        transactionGoal.setId(1L);


        when(transactionGoalService.createTransactionGoal(transactionGoalRequestDto)).thenReturn(transactionGoal);

        System.out.println(gson.toJson(transactionGoalRequestDto));

        mockMvc.perform(post("/transactionGoal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(transactionGoalRequestDto)))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    @DisplayName("DELETE `/transactionGoal/{id}`")
    void deleteTransactionById() throws Exception {

        Long goalId = 1L;

        doNothing().when(transactionGoalService).deleteByGoalId(goalId);

        mockMvc.perform(delete("/transactionGoal/"+ goalId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}