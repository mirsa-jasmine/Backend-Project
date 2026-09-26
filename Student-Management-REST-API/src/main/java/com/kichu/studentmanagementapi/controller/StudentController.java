package com.kichu.studentmanagementapi.controller;
import com.kichu.studentmanagementapi.dto.StudentRequest;
import com.kichu.studentmanagementapi.dto.StudentResponse;
import com.kichu.studentmanagementapi.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;
import java.util.List;
import jakarta.validation.Valid;
@RestController
public class StudentController{
    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping("/students")
    public List<StudentResponse> getStudents() {
        return studentService.getAllStudents();
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable int id){
        StudentResponse response= studentService.getStudentById(id);

        return ResponseEntity.ok(response);

    }
    @PostMapping("/students")
    public ResponseEntity<StudentResponse> addStudent(@Valid@RequestBody StudentRequest request){
        StudentResponse response=studentService.addStudent(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable int id,@Valid @RequestBody StudentRequest request){
        StudentResponse result=studentService.updateStudent(id,request);

            return ResponseEntity.ok(result);

    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();

    }
}
