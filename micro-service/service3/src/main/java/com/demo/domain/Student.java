package com.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column
    private String name;
    @Column(unique = true)
    private String email;
    @Column
    private int years;
    @Column
    private double averageReversibility;
}
