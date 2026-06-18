package com.example.householdaccountapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date;
    private String category;
    private Integer amount;
    private LocalDateTime createdAt;
    private String paidBy;      // 支払った人（例: "自分", "相手"）
    private boolean isShared;   // 折半対象なら true
}