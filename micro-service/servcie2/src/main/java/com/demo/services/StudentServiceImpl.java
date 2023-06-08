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
    private StudentRepository studentRepo;
    @Resource
    private YearRepository yearRepository;

    @Override
    public Boolean saveStatisticFromBD() {
        List<Student> someStudent = studentRepo.findAll();
        List<YearsFullDTO> yearsFullDTOS = new ArrayList<>();


        TreeSet <Integer> years= new TreeSet<>();
        for (Student st:someStudent) {
            years.add(st.getYears());
        }

        for (Integer element:years) {
            List<Student> someStudentYaer=someStudent.stream().filter(student -> student.getYears()==element)
                    .collect(Collectors.toList());
            double sumOfAverageGrades = someStudentYaer.stream().mapToDouble(Student::getAverageReversibility).sum();

            YearsFullDTO yearsFullDTO = new YearsFullDTO();
            yearsFullDTO.setYear(element);
            double AverageReversibility=Math.round((sumOfAverageGrades/someStudentYaer.size())*10);
            yearsFullDTO.setAverageReversibility(AverageReversibility/10);

            yearsFullDTOS.add(yearsFullDTO);
        }

        List<Years> years1 = new ArrayList<>();

        for (YearsFullDTO it:yearsFullDTOS) {
            Years years2 = new Years();
            years2.setYear(it.getYear());
            years2.setAverageReversibility(it.getAverageReversibility());
            years1.add(years2);
        }
        List<Years> result = yearRepository.saveAll(years1);
        return result.size() == years1.size();
    }

    @Override
    public ArrayList<YearsFullDTO> getAllYearsStat() {
        List<Years> someYears = yearRepository.findAll();

        ArrayList<YearsFullDTO> yearsFullDTOS = new ArrayList<>();

        for (Years years : someYears) {
            YearsFullDTO yearsFullDTO = new YearsFullDTO();
            yearsFullDTO.setYear(years.getYear());
            yearsFullDTO.setAverageReversibility(years.getAverageReversibility());
            yearsFullDTOS.add(yearsFullDTO);
        }

        return yearsFullDTOS;
    }
}
