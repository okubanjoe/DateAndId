package com.ornate.customerservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "goal_category")
@Data
public class GoalCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    private String description;

}
