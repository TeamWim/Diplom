package com.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentFullDTO {
    private int id;
    private String name;
    private String email;
    private int years;
    private double averageReversibility;
}
