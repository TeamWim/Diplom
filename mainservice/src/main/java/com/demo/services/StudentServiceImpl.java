package com.demo.services;

import com.demo.domain.Student;
import com.demo.domain.Years;
import com.demo.dto.StudentFullDTO;
import com.demo.dto.StudentSafeDTO;
import com.demo.dto.YearsFullDTO;
import com.demo.reposetories.StudentRepository;
import com.demo.reposetories.YearsRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentRepository studentRepo;
    @Resource
    private YearsRepository yearRepository;

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

    @Override
    public Boolean saveStatisticFromBD() {
        List<Student> someStudent = studentRepo.findAll();
        List<YearsFullDTO> yearsFullDTOS = new ArrayList<>();


        TreeSet<Integer> years= new TreeSet<>();
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
