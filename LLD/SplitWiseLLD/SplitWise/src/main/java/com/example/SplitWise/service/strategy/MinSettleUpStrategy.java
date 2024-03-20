package com.example.SplitWise.service.strategy;

import com.example.SplitWise.model.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class MinSettleUpStrategy implements SettleUpStrategy{

    @Getter
    @Setter
    class UserDue {
        private User user;
        private double amount;

        public UserDue(User user, double amount) {
            this.user = user;
            this.amount = amount;
        }
    }

    @Override
    public List<SettlementTransaction> getSettlementTransactions(List<Expense> expenses) {
        PriorityQueue<UserDue> minHeap = new PriorityQueue<>((a, b)->Double.compare(a.getAmount(), b.getAmount()));
        PriorityQueue<UserDue> maxHeap = new PriorityQueue<>((a, b)->Double.compare(b.getAmount(), a.getAmount()));
        HashMap<User, Double> map = new HashMap<User, Double>();

        for (Expense expense : expenses) {
            for (int j = 0; j < expense.getUserExpenses().size(); j++) {
                UserExpense userExpense = expense.getUserExpenses().get(j);
                User user = userExpense.getUser();
                double amount = userExpense.getAmount();
                if (userExpense.getUserExpenseType() == UserExpenseType.PAID) {
                    if (map.containsKey(user)) {
                        double amountExisting = map.get(user);
                        map.put(user, amountExisting + amount);
                    } else {
                        map.put(user, amount);
                    }
                } else {
                    if (map.containsKey(user)) {
                        double amountExisting = map.get(user);
                        map.put(user, amountExisting - amount);
                    } else {
                        map.put(user, -amount);
                    }
                }
            }
        }

        for (User user: map.keySet()) {
            double amount = map.get(user);
            UserDue userDue = new UserDue(user, amount);
            if (amount>0) {
                maxHeap.add(userDue);
            }
            else if (amount<0){
                minHeap.add(userDue);
            }
        }

        List<SettlementTransaction> transactionList = new ArrayList<>();
        while(minHeap.size()>0 && maxHeap.size()>0) {
            UserDue lender = maxHeap.peek();
            maxHeap.remove();

            UserDue borrower = minHeap.peek();
            minHeap.remove();

            double lenderAmount = lender.getAmount();
            double borrowerAmount = (-1)*borrower.getAmount();
            SettlementTransaction settlementTransaction = new SettlementTransaction();
            if (lenderAmount>borrowerAmount) {
                settlementTransaction.setLendor(lender.getUser());
                settlementTransaction.setBorrower(borrower.getUser());
                settlementTransaction.setAmount(borrowerAmount);
                lenderAmount-=borrowerAmount;
                lender.setAmount(lenderAmount);
                maxHeap.add(lender);
            }
            else if(lenderAmount<borrowerAmount) {
                settlementTransaction.setLendor(lender.getUser());
                settlementTransaction.setBorrower(borrower.getUser());
                settlementTransaction.setAmount(lenderAmount);
                borrowerAmount-=lenderAmount;
                borrower.setAmount((-1)*borrowerAmount);
                minHeap.add(borrower);
            }
            else {
                settlementTransaction.setLendor(lender.getUser());
                settlementTransaction.setBorrower(borrower.getUser());
                settlementTransaction.setAmount(lenderAmount);
            }
            transactionList.add(settlementTransaction);
        }
        return transactionList;
    }
}