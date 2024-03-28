package com.zycode.demo.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("student")
public class StudentController {

    @GetMapping
    public List<Student> getAllStudent(){
        return List.of(
                new Student(UUID.randomUUID(),"James","Bond","jamesbond@gamail.com",Student.Gender.MALE),
                new Student(UUID.randomUUID(),"Elisa","Tamara","elisatamara@gmail.com",Student.Gender.FEMALE)
        );
    }
}
