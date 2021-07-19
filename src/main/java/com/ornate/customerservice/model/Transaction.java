package com.ornate.customerservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "transaction")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double charge;
    @Column(name ="reference", unique = true)
    private String reference;
    private Double amount;
    private Instant createdDate;
    private Instant updatedDate;
    private String createdBy;
    private String updatedBy;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private TransactionGoal transactionGoal;


    //    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
//    @JoinColumn(name = "iduser", insertable = false, updatable = false)
}

