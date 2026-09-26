package com.kichu.studentmanagementapi.service;
import com.kichu.studentmanagementapi.dto.StudentRequest;
import com.kichu.studentmanagementapi.dto.StudentResponse;
import com.kichu.studentmanagementapi.exception.StudentNotFoundException;
import com.kichu.studentmanagementapi.mapper.StudentMapper;
import com.kichu.studentmanagementapi.model.Student;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService{
    private int nextId;

    private final StudentMapper studentMapper;
    public StudentService(StudentMapper studentMapper){
        this.studentMapper=studentMapper;
        if(!students.isEmpty()){
            nextId=0;
            for(Student student : students){
                if(nextId<student.getId()){
                    nextId=student.getId();
                }
            }
            nextId++;
        }else{
            nextId=1;
        }


    }
    private final List<Student> students=new ArrayList<>(List.of(
            new Student(1, "Alice", "CSE", 20),
            new Student(2, "Bob", "ECE", 21),
            new Student(3, "Charlie", "ME", 22)
        )
    );
    public List<StudentResponse> getAllStudents(){
        List<StudentResponse> response=new ArrayList<>();
        for(Student student:students){
            response.add(studentMapper.toResponse(student ));
        }
        return response;
    }
    public StudentResponse getStudentById(int id){
        for(Student student:students){
            if(student.getId()==id){
                return studentMapper.toResponse(student );
            }
        }throw new StudentNotFoundException("Student with id "+id+" not found" );
    }
    public StudentResponse addStudent(StudentRequest request){
        int id=nextId++;
        Student student=studentMapper.toStudent(request,id);
        students.add(student);

        return studentMapper.toResponse(student);
    }
    public StudentResponse updateStudent(int id, StudentRequest request) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.setName(request.getName());
                s.setDepartment(request.getDepartment());
                s.setAge(request.getAge());

                return studentMapper.toResponse(s);
            }
        }

        throw new StudentNotFoundException("Student with id " + id + " not found");
    }
    public void deleteStudent(int id){
        for(Student s:students) {
            if(s.getId()==id){
                students.remove(s);
                return;
            }
        }throw new StudentNotFoundException("Student with id "+id+" not found" );
    }

}
