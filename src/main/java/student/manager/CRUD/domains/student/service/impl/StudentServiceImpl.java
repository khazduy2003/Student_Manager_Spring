package student.manager.CRUD.domains.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

        boolean check = studentRepository.existsById(id);
        if (check) {
            return studentRepository.getStudentById(id);
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

        boolean check = studentRepository.existsById(id);
        if (check) {
            studentRepository.deleteById(id);
            studentRepository.flush();
            return "Delete success!";
        }
        throw new StudentException("ID has not exist");
    }

    // Change information
    public String changeInfo(Long id, ChangeStudentRequest request) {

        boolean check = studentRepository.existsById(id);

        if (!check) {
            throw new StudentException("ID has not exist");
        }

        Student student = studentRepository.getStudentById(id);

        if (request.getName()== null && request.getDob()==null && request.getAddress()==null) {
            throw new StudentException("Fill in username, dob or address");
        }
        if (request.getNewName()== null && request.getNewDob()==null && request.getNewAddress()==null) {
            throw new StudentException("Invalid new information");
        }
        if ((request.getName()!=null && request.getNewName()==null)
                || (request.getDob()!=null && request.getNewDob()==null)
                || (request.getAddress()!=null && request.getNewAddress()==null) ) {
            throw new StudentException("Must fill in new name, new day of birth or new address");
        }

        if ((request.getNewName()!=null && request.getName()==null)
                || (request.getNewDob()!=null && request.getDob()==null)
                || (request.getNewAddress()!=null && request.getAddress()==null) ) {
            throw new StudentException("Must fill in name, day of birth or address");
        }

        if ( (request.getName()!=null && !request.getName().equals(student.getName()))
                || (request.getDob()!=null && !request.getDob().equals(student.getDob()))
                || (request.getAddress()!=null && !request.getAddress().equals(student.getAddress())) ) {
            throw new StudentException("Incorrect name, day of birth or address");
        }


        if ( (request.getName()!=null && request.getNewName().equals(request.getName()))
                || (request.getDob()!=null && request.getDob().equals(request.getNewDob()))
                || (request.getAddress()!=null && request.getAddress().equals(request.getNewAddress())) ) {
            throw new StudentException("Duplicate new name, new day of birth or new address");
        }

        if (request.getNewName()!=null) {
            student.setName(request.getNewName());
        }
        if (request.getNewDob()!=null) {
            student.setDob(request.getNewDob());
        }
        if (request.getNewAddress()!=null) {
            student.setAddress(request.getNewAddress());
        }

        studentRepository.saveAndFlush(student);

        return "Change Information Success !";
    }

}
