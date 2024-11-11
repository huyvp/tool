package com.tool.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Entity
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String knoxId;
    String ip;
    String username;
    String password;
    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles;
}
