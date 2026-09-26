package com.kichu.studentmanagementapi.mapper;
import com.kichu.studentmanagementapi.dto.StudentRequest;
import com.kichu.studentmanagementapi.dto.StudentResponse;
import com.kichu.studentmanagementapi.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public Student toStudent(StudentRequest request,int id){
        return new Student(id,request.getName(),request.getDepartment(),request.getAge());
    }
    public StudentResponse toResponse(Student student){
        return new StudentResponse(student.getId(),student.getName(),student.getDepartment(),student.getAge());
    }
}
