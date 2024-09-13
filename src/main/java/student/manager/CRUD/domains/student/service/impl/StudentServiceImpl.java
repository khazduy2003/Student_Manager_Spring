package student.manager.CRUD.domains.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import student.manager.CRUD.application.exceptions.StudentException;
import student.manager.CRUD.domains.student.model.entity.Student;
import student.manager.CRUD.domains.student.model.request.ChangeStudentRequest;
import student.manager.CRUD.domains.student.model.request.StudentRequest;
import student.manager.CRUD.domains.student.repository.StudentRepository;
import student.manager.CRUD.domains.student.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student getStudentById(Long id) {
        if (studentRepository.existsById(id)) {
            return studentRepository.getReferenceById(id);
        }
        throw new StudentException("ID has not exist");
    }

    // Get All of Student
    @Override
    public List<Student> getAllOfStudent() {
        return studentRepository.findAll();
    }

    // Add Student
    @Override
    public StudentRequest addStudent(StudentRequest request) {

        boolean check=true;
        boolean checkValid=true;
        if ( request.getName()== null && request.getDob()==null && request.getAddress()==null) {
            check=false;
        }
        else if (request.getName()== null || request.getDob()==null || request.getAddress()==null){
            checkValid=false;
        }

        if (!check) {
            throw new StudentException("Fill in username, dob and address");
        }
        if (!checkValid) {
            throw new StudentException("Invalid username, dob or address");
        }

        Student student = new Student(request.getName(),request.getDob(),request.getAddress());
        studentRepository.saveAndFlush(student);
        return request;
    }

    // Delete Student
    public String deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            studentRepository.flush();
            return "Delete success!";
        }
        throw new StudentException("ID has not exist");
    }

    // Change information
    public String changeInfo(Long id, ChangeStudentRequest request) {

        boolean check= studentRepository.existsById(id);
        if (!check) {
            throw new StudentException("ID has not exist");
        }

        if (request.getName()== null && request.getDob()==null && request.getAddress()==null) {
            throw new StudentException("Fill in username, dob and address");
        }
        if (request.getName()== null || request.getDob()==null || request.getAddress()==null) {
            throw new StudentException("Invalid username, dob or address");
        }

        studentRepository.getReferenceById(id).setName(request.getNewName());
        studentRepository.getReferenceById(id).setDob(request.getNewDob());
        studentRepository.getReferenceById(id).setAddress(request.getNewAddress());
        studentRepository.flush();

        return "Change Information Success !";
    }

}
