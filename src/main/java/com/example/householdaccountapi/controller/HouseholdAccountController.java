package com.example.householdaccountapi.controller;

import com.example.householdaccountapi.entity.Expense;
import com.example.householdaccountapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.householdaccount.dto.SettlementResult;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/accounts")
public class HouseholdAccountController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createExpense(@RequestBody Map<String, Object> expenseData) {
        try {
            Expense expense = new Expense();
            expense.setDate((String) expenseData.get("date"));
            expense.setCategory((String) expenseData.get("category"));
            expense.setAmount(Integer.parseInt(expenseData.get("amount").toString()));

            Object isShared = expenseData.get("isShared");
            expense.setShared(isShared != null && (boolean) isShared);

            expense.setCreatedAt(LocalDateTime.now());

            expenseRepository.save(expense);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "データベースへの保存に成功しました！");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "登録に失敗しました: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/summary/settlement")
    public SettlementResult getSettlement() {
        // 1. 折半対象のリストを全件取得する
        List<Expense> sharedList = expenseRepository.findByIsSharedTrue();

        // 2. 合計額を計算する
        double totalShared = sharedList.stream().mapToDouble(Expense::getAmount).sum();

        // 3. 必要な情報を詰め込んで返す
        return new SettlementResult(totalShared, totalShared / 2.0, sharedList);
    }
}