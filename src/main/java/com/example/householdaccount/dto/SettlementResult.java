package com.example.householdaccount.dto;

import com.example.householdaccountapi.entity.Expense; // ここを修正しました！
import java.util.List;

public class SettlementResult {
    private double totalAmount;
    private double perPerson;
    private List<Expense> sharedExpenses;

    // コンストラクタ
    public SettlementResult(double totalAmount, double perPerson, List<Expense> sharedExpenses) {
        this.totalAmount = totalAmount;
        this.perPerson = perPerson;
        this.sharedExpenses = sharedExpenses;
    }

    // GetterとSetter
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public double getPerPerson() { return perPerson; }
    public void setPerPerson(double perPerson) { this.perPerson = perPerson; }

    public List<Expense> getSharedExpenses() { return sharedExpenses; }
    public void setSharedExpenses(List<Expense> sharedExpenses) { this.sharedExpenses = sharedExpenses; }
}