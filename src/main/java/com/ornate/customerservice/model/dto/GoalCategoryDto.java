package com.ornate.customerservice.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class GoalCategoryDto implements Serializable {

    private String categoryName;
    private String description;
}
