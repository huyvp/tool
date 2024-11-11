package com.tool.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String environment;
    String type;
    String username;
    String password;
    String updatedBy;
    Timestamp updatedAt;
    boolean active;
}
