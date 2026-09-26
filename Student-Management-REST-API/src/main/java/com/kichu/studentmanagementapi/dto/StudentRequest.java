package com.kichu.studentmanagementapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class StudentRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Department cannot be blank")
    private String department;

    @Min(value = 17, message = "Age must be at least 17")
    private int age;

    public StudentRequest() {
    }

    public StudentRequest(String name, String department, int age) {
        this.name = name;
        this.department = department;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}





