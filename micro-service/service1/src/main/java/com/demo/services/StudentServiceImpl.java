package com.demo.services;

import com.demo.domain.Student;
import com.demo.dto.StudentFullDTO;
import com.demo.dto.StudentSafeDTO;
import com.demo.reposetories.StudentRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentRepository studentRepo;

    @Override
    public StudentFullDTO saveStudent(StudentSafeDTO student) {
        Random random = new Random();
        double min = 1;
        double max = 101;
        double randomNumber =Math.round((random.nextDouble(max - min + 1) + min)*10)/10.0;

        Student someStudent = new Student();
        someStudent.setName(student.getName());
        someStudent.setEmail(student.getEmail());
        someStudent.setYears(student.getYears());
        someStudent.setAverageReversibility(randomNumber);
        StudentFullDTO studentDTO = new StudentFullDTO();

        studentRepo.save(someStudent);
        studentDTO.setId(someStudent.getId());
        studentDTO.setName(someStudent.getName());
        studentDTO.setEmail(someStudent.getEmail());
        studentDTO.setYears(someStudent.getYears());
        studentDTO.setAverageReversibility(someStudent.getAverageReversibility());

        return studentDTO;
    }

    @Override
    public StudentFullDTO getStudent(Integer id) {
        Student someStudent = studentRepo.getById(id);
        StudentFullDTO studentDTO = new StudentFullDTO();
        studentDTO.setId(someStudent.getId());
        studentDTO.setName(someStudent.getName());
        studentDTO.setEmail(someStudent.getEmail());
        studentDTO.setAverageReversibility(someStudent.getAverageReversibility());
        return studentDTO;
    }

    @Override
    public ArrayList<StudentFullDTO> getAllStudent() {
        List<Student> someStudent = studentRepo.findAll();

        ArrayList<StudentFullDTO> studentDTO = new ArrayList<>();

        for (Student student : someStudent) {
            StudentFullDTO studentFullDTO = new StudentFullDTO();
            studentFullDTO.setId(student.getId());
            studentFullDTO.setName(student.getName());
            studentFullDTO.setEmail(student.getEmail());
            studentFullDTO.setYears(student.getYears());
            studentFullDTO.setAverageReversibility(student.getAverageReversibility());
            studentDTO.add(studentFullDTO);
        }

        return studentDTO;
    }
}
