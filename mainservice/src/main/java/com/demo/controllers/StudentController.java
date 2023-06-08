package com.demo.controllers;

import com.demo.dto.StudentFullDTO;
import com.demo.dto.StudentSafeDTO;
import com.demo.dto.YearsFullDTO;
import com.demo.services.StudentService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/main-service")
@CrossOrigin(origins = "*")
public class StudentController {
    @Resource
    private StudentService studentService;


    @PostMapping("/create")
    public ResponseEntity<StudentFullDTO> createStudent(@RequestBody StudentSafeDTO student) {
        return ResponseEntity.ok(studentService.saveStudent(student));
    }
    @GetMapping("/{id}")
    public ResponseEntity <StudentFullDTO> getStudent(@PathVariable("id") Integer id){

        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @GetMapping("/all")
    public ResponseEntity <List<StudentFullDTO>> getAllStudent(){
        return ResponseEntity.ok(studentService.getAllStudent());
    }
    @Resource
    private StudentService avarageResponsForYearService;

    @PostMapping("/create-statistic-by-years")
    public ResponseEntity<Boolean> createStudent() {
        return ResponseEntity.ok(avarageResponsForYearService.saveStatisticFromBD());
    }


    @GetMapping("/get-statistic-by-years")
    public ResponseEntity <List<YearsFullDTO>> getAllYearsStat(){

        return ResponseEntity.ok(avarageResponsForYearService.getAllYearsStat());
    }

    @GetMapping("/get-success-years")
    public ResponseEntity <YearsFullDTO> getSuccessYear(){

        return ResponseEntity.ok(avarageResponsForYearService.getSuccessYear());
    }
}
