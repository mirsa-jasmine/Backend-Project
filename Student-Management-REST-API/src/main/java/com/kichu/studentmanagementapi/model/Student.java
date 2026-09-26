package com.kichu.studentmanagementapi.model;

public class Student {

    private final int id;
    private String name;
    private String department;
    private int age;

    public Student(int id, String name, String department, int age) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.age = age;
    }

    public int getId() {
        return id;
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

    public boolean setAge(int age) {
        if (age < 0 || age > 100) {
            return false;
        }
        this.age = age;
        return true;
    }

    public boolean setName(String name) {
        if (name != null) {
            name = name.trim();
            if (name.isEmpty()) {
                return false;
            } else {
                this.name = name;
                return true;
            }
        }
        return false;
    }

    public boolean setDepartment(String department) {
        if (department != null) {
            department = department.trim();
            if (department.isEmpty()) {
                return false;
            } else {
                this.department = department;
                return true;
            }
        }
        return false;
    }
}
