package com.studentmanagement.service;
import com.studentmanagement.model.Student;
import java.util.List;
public interface StudentOperations{
    boolean addStudent(Student S);
    Student findStudent(int id);
    boolean removeStudent(int id);
    int getTotalStudents();
    Student oldestStudent();
    boolean updateStudent(int id,String name,int age);
    List<Student> getAllStudents();
    List<Student> searchByName(String key);
    List<Student>getStudentsSortedByAge();
}
