package com.studentmanagement;
import com.studentmanagement.service.StudentOperations;
import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
public class Main {

    public static int getIntInput(Scanner sc){
        while(true){
            try{
                return sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Invalid input. Enter a number:");
                sc.next();
            }
        }
    }
    public static String getStringInput(Scanner sc){
        return sc.nextLine();
    }
    public static void main(String[] args) {

        StudentService service=new StudentService();
        StudentOperations ops=service;
        service.loadStudentsFromFile();
        Scanner sc=new Scanner(System.in);
        int id,choice,age;
        String name;
        while(true){
            System.out.println("Enter your choice?");
            System.out.println("\t---Menu Options---");
            System.out.println("\t1.Add Student");
            System.out.println("\t2.Find Student");
            System.out.println("\t3.Total Student");
            System.out.println("\t4.Oldest Student");
            System.out.println("\t5.updateStudent");
            System.out.println("\t6.All Student details");
            System.out.println("\t7.Search By Name");
            System.out.println("\t8.Sort By Age");
            System.out.println("\t9.Exiting");
            choice=getIntInput(sc);
            sc.nextLine();
            switch(choice){
                case 1:
                {
                    System.out.println("Enter the id of new student:");
                    id=getIntInput(sc);
                    sc.nextLine();
                    System.out.println("Enter the name of new student:");
                    name=getStringInput(sc);
                    System.out.println("Enter the age of new student:");
                    age=getIntInput(sc);
                    sc.nextLine();
                    boolean added=ops.addStudent(new Student(id, name, age));
                    if(added)
                        System.out.println("New Student is added!");
                    else
                        System.out.println("Student already exists or Invalid data");
                    service.saveStudentsToFile();
                    break;
                }
                case 2:{
                    System.out.println("Enter the id to search:");
                    id=getIntInput(sc);
                    sc.nextLine();
                    Student s= ops.findStudent(id);
                    if(s!=null){
                        System.out.println("ID: "+s.getId()+" | NAME: "+s.getName()+" | AGE: "+s.getAge());
                        System.out.println("Would you like to remove this student");
                        String remove=getStringInput(sc);
                        if(remove.equalsIgnoreCase("yes")) {
                            boolean removed = ops.removeStudent(id);
                            if (removed) {
                                System.out.println("student is removed");
                                service.saveStudentsToFile();
                            } else{
                                System.out.println("student doesn't exist");
                            }
                        }
                    }
                    else
                        System.out.println("student doesn't exist");
                    break;
                }
                case 3:{
                    System.out.println("Total students: "+ops.getTotalStudents());
                    break;
                }
                case 4:{
                    Student oldest=ops.oldestStudent();
                    if(oldest!=null){
                        System.out.println("Oldest student is "+oldest.getName());
                    }
                    else
                        System.out.println("There is no students to check");
                    break;
                }
                case 5:{
                    System.out.println("Enter the id of updating student:");
                    id=getIntInput(sc);
                    sc.nextLine();
                    System.out.println("Enter the name of updating student:");
                    name=getStringInput(sc);

                    System.out.println("Enter the age of updating student:");
                    age=getIntInput(sc);
                    sc.nextLine();
                    boolean update=ops.updateStudent(id,name,age);
                    if(update) {
                        System.out.println("Student details updated");
                        service.saveStudentsToFile();
                    }
                    else
                        System.out.println("Invalid data or student not found");

                    break;
                }
                case 6:{
                    List<Student> allStudents=ops.getAllStudents();
                    if(allStudents.isEmpty()){
                        System.out.println("No student found");
                    }else{
                        for(Student s:allStudents){
                            System.out.println("ID: "+s.getId()+" | NAME: "+s.getName()+" | AGE: "+s.getAge());
                        }
                    }
                    break;
                }
                case 7:{
                    String key;
                    System.out.println("Enter the name to search:");
                    key=getStringInput(sc);
                    List<Student> searchStudent =ops.searchByName(key);
                    if(searchStudent.isEmpty()){
                        System.out.println("No matching student found");
                    }else{
                        for(Student s:searchStudent){
                            System.out.println("ID: "+s.getId()+" | NAME: "+s.getName()+" | AGE: "+s.getAge());
                        }
                    }
                    break;
                }
                case 8:{
                    List<Student> sortedList=ops.getStudentsSortedByAge();
                    if(sortedList.isEmpty()){
                        System.out.println("No  student available");
                    }else {
                        for(Student s:sortedList){
                            System.out.println("ID: "+s.getId()+" | NAME: "+s.getName()+" | AGE: "+s.getAge());
                        }
                    }
                    break;
                }
                case 9:{

                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }

                default:
                {
                    System.out.println("Invalid choice");
                    break;
                }
            }
        }
    }

}


