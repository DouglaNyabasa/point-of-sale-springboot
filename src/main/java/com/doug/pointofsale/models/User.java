package com.doug.pointofsale.models;

import com.doug.pointofsale.domain.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String password;


    @Column(nullable = false, unique = true)
    @Email(message = "Email should be valid !!")
    private String email;

    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = false)
    private String phoneNumber;

    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate lastLoginAt;



}
