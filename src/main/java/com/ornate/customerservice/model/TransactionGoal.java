package com.ornate.customerservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "transaction_goal")
@Data
public class TransactionGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goalName;

    @ManyToOne
    @JoinColumn(name = "goal_category_id")
    private GoalCategory goalCategory;


}
