package com.studentmanagement.model;
public class Student{
    private final int id;
    private  int age;
    private  String name;
    public Student(int id,String name,int age){
        this.id=id;
        this.age=age;
        this.name=name;
    }
    public boolean setName(String name){
        if(name!=null) {
            name = name.trim();
            if (name.isEmpty()) {
                return false;
            }else {
                this.name = name;
                return true;
            }
        }return false;
    }
    public boolean setAge(int age){
        if(age<0||age>100){
            return false;
        }
        this.age=age;
        return true;
    }
    public int getId(){
        return id;
    }
    public int getAge(){
        return age;
    }
    public String getName(){
        return name;
    }

}
