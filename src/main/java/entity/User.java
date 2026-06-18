package com.example.householdaccountapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity                  // これがデータベースのテーブルと連動するクラスだよという合図
@Table(name = "users")   // MySQLの「users」テーブルと紐付けます
@Data                    // Lombok機能：Getter/Setterを自動で用意してくれます
public class User {

    @Id // 主キー（PK）の設定
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENTと同じ意味
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;
}