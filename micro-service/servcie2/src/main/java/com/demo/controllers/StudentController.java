package com.demo.controllers;

import com.demo.dto.StudentFullDTO;
import com.demo.dto.StudentSafeDTO;
import com.demo.dto.YearsFullDTO;
import com.demo.services.AvarageResponsForYearService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statistic")
@CrossOrigin(origins = "*")
public class StudentController {
    @Resource
    private AvarageResponsForYearService avarageResponsForYearService;

    @PostMapping("/create-statistic-by-years")
    public ResponseEntity<Boolean> createStudent() {
        System.out.println("1111");
        return ResponseEntity.ok(avarageResponsForYearService.saveStatisticFromBD());
    }


    @GetMapping("/get-statistic-by-years")
    public ResponseEntity <List<YearsFullDTO>> getAllYearsStat(){

        return ResponseEntity.ok(avarageResponsForYearService.getAllYearsStat());
    }
}
