package com.demo.services;

import com.demo.domain.Student;
import com.demo.domain.Years;
import com.demo.dto.StudentFullDTO;
import com.demo.dto.StudentSafeDTO;
import com.demo.dto.YearsFullDTO;
import com.demo.reposetories.StudentRepository;
import com.demo.reposetories.YearRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements AvarageResponsForYearService {
    @Resource
    private YearRepository yearRepository;
    @Override
    public YearsFullDTO getSuccessYear() {
        List<Years> someYears = yearRepository.findAll();

        YearsFullDTO yearsFullDTOS = new YearsFullDTO();
        yearsFullDTOS.setAverageReversibility(Integer.MIN_VALUE);

        for (int i = 0; i < someYears.size(); i++) {
            if (someYears.get(i).getAverageReversibility()>yearsFullDTOS.getAverageReversibility()){
                yearsFullDTOS.setYear(someYears.get(i).getYear());
                yearsFullDTOS.setAverageReversibility(someYears.get(i).getAverageReversibility());
            }

        }



        return yearsFullDTOS;
    }
}
