package com.studentmanagement.service;
import com.studentmanagement.model.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Comparator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class StudentService implements StudentOperations{
    private final HashMap<Integer, Student>students=new HashMap<>();
    public boolean addStudent(Student s){
        if(students.containsKey(s.getId())){
            return false;
        }
        if(s.getName()==null || s.getName().trim().isEmpty() || s.getAge()<0||s.getAge()>100 ){
            return false;
        }
        students.put(s.getId(), s);
        return true;
    }
    public Student findStudent(int id){
        return students.get(id);
    }
    public boolean removeStudent(int id){
        if(students.containsKey(id)){
            students.remove(id);
            return true;
        }
        return false;
    }
    public int getTotalStudents(){
        return students.size();
    }
    public Student oldestStudent(){
        Student oldest=null;
        for(Student s:students.values()){
            if(oldest==null||s.getAge()>oldest.getAge())
                oldest=s;
        }
        return oldest;
    }
    public boolean updateStudent(int id,String name,int age){
        Student student=students.get(id);
        if(student!=null){
            if((age<0||age>100) || (name==null)|| (name.trim().isEmpty()) ){
                return false;
            }else{
                if(student.setName(name)&& student.setAge(age)){
                    return true;
                }
            }
        }
        return false;
    }
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        for (Student s : students.values()) {
            list.add(s);
        }
        return list;
    }
    public List<Student> searchByName(String key){
        key=key.toLowerCase();
        List<Student>list=new ArrayList<>();
        for(Student s:students.values()){
            String s1=s.getName().toLowerCase();
            if(s1.contains(key)){
                list.add(s);
            }
        }
        return list;
    }
    private static final Comparator<Student>AGE_THEN_ID_COMPARATOR=Comparator.comparing(Student::getAge).thenComparingInt(Student::getId);
    public List<Student> getStudentsSortedByAge(){
        List<Student> list=new ArrayList<>(students.values());
        list.sort(AGE_THEN_ID_COMPARATOR);
        return list;
    }
    public void saveStudentsToFile(){

        try{
            BufferedWriter writer=new BufferedWriter(new FileWriter("students.txt"));
            for(Student s:students.values()){
                writer.write(s.getId()+","+s.getName()+","+s.getAge());
                writer.newLine();
            }writer.close();
        }catch(IOException e){
            System.out.println("Error saving students details");
        }
    }
    public void loadStudentsFromFile(){
        File file=new File("students.txt");
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
            String line;
            while((line=reader.readLine())!=null){
                if(line.trim().isEmpty())
                    continue;
                String [] parts=line.split(",");
                if(parts.length!=3)
                    continue;
                try{
                    int id=Integer.parseInt(parts[0]);
                    String name=parts[1];
                    int age=Integer.parseInt(parts[2]);
                    students.put(id,new Student(id, name, age));
                }catch(NumberFormatException e){
                    System.out.println("Invalid no format in file "+line);
                }

            }reader.close();
        }catch(IOException e){
            System.out.println("Error loading students details from file");
        }
    }
}