package com.demo.services;

import com.demo.dto.StudentFullDTO;
import com.demo.dto.StudentSafeDTO;

import java.util.ArrayList;

public interface StudentService {
    public StudentFullDTO saveStudent (StudentSafeDTO student);
    public StudentFullDTO getStudent (Integer id);
    public ArrayList<StudentFullDTO> getAllStudent();
}
