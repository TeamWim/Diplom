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
@RequestMapping("/api/success")
@CrossOrigin(origins = "*")
public class StudentController {
    @Resource
    private AvarageResponsForYearService avarageResponsForYearService;

    @GetMapping("/get-success-years")
    public ResponseEntity <YearsFullDTO> getSuccessYear(){

        return ResponseEntity.ok(avarageResponsForYearService.getSuccessYear());
    }
}
