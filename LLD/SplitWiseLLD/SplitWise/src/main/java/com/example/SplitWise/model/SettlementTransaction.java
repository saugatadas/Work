package com.example.SplitWise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SettlementTransaction extends BaseModel {
    @ManyToOne
    private User borrower;
    @ManyToOne
    private User lendor;
    private double amount;
}
