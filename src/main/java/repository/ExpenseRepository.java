package com.example.householdaccountapi.repository;

import com.example.householdaccountapi.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // これを忘れずに！
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    // ここに移動させます！
    @Query("SELECT e.category, SUM(e.amount) FROM Expense e GROUP BY e.category")
    List<Object[]> findTotalAmountByCategory();
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.isShared = true")
    Double findTotalSharedAmount();
    List<Expense> findByIsSharedTrue();
}