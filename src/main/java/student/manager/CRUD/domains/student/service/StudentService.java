package student.manager.CRUD.domains.student.service;

import org.springframework.web.bind.annotation.RequestBody;
import student.manager.CRUD.domains.student.model.entity.Student;
import student.manager.CRUD.domains.student.model.request.ChangeStudentRequest;
import student.manager.CRUD.domains.student.model.request.StudentRequest;

import java.util.List;

public interface StudentService {

    Student getStudentById(Long id);

    // Get All of Student
    List<Student> getAllOfStudent();

    // Add student
    StudentRequest addStudent(StudentRequest request);

    // Delete Student
    String deleteStudent(Long id);

    String changeInfo(Long id, ChangeStudentRequest request);
}
