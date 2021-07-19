package com.ornate.customerservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "transaction_goal")
@Data
public class TransactionGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goalName;


}
