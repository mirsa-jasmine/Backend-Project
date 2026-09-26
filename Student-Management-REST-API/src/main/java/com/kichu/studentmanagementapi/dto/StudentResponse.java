package com.kichu.studentmanagementapi.dto;

public class StudentResponse {
    private int age;
    private String name;
    private String department;
    private final int id;
    public StudentResponse(int id, String name, String department, int age) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.age = age;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getDepartment(){
        return department;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){

        this.age=age;

    }
    public void setName(String name){

        this.name=name;

    }
    public void setDepartment(String department){

        this.department=department;
    }
}
