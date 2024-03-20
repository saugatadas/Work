package com.example.SplitWise.service.strategy;

import com.example.SplitWise.model.Expense;
import com.example.SplitWise.model.SettlementTransaction;

import java.util.List;

public interface SettleUpStrategy {
    List<SettlementTransaction> getSettlementTransactions(List<Expense> expenses);
}
