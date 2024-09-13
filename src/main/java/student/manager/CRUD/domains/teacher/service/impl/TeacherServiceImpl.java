package student.manager.CRUD.domains.teacher.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.manager.CRUD.application.exceptions.StudentException;
import student.manager.CRUD.application.exceptions.TeacherException;
import student.manager.CRUD.domains.student.model.entity.Student;
import student.manager.CRUD.domains.teacher.model.entity.Teacher;
import student.manager.CRUD.domains.teacher.model.request.ChangeTeacherRequest;
import student.manager.CRUD.domains.teacher.model.request.TeacherRequest;
import student.manager.CRUD.domains.teacher.repository.TeacherRepository;
import student.manager.CRUD.domains.teacher.service.TeacherService;

import java.util.List;

@Service
public class TeacherServiceImpl  implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    public Teacher getTeacherById(Long id) {
        if (teacherRepository.existsById(id)) {
            return teacherRepository.getReferenceById(id);
        }
        throw new TeacherException("ID has not exist");
    }

    public List<Teacher> getAllOfTeacher() {
        return teacherRepository.findAll();
    }

    public TeacherRequest addTeacher(TeacherRequest request) {
        boolean check=true;
        boolean checkValid=true;
        if ( request.getName()== null && request.getDob()==null && request.getAddress()==null && request.getDepartment()==null) {
            check=false;
        }
        else if (request.getName()== null || request.getDob()==null || request.getAddress()==null || request.getDepartment()==null){
            checkValid=false;
        }

        if (!check) {
            throw new TeacherException("Fill in username, dob and address");
        }
        if (!checkValid) {
            throw new TeacherException("Invalid username, dob or address");
        }
        Teacher teacher = new Teacher(request.getName(),request.getDob(),request.getDepartment(),request.getAddress());
        teacherRepository.saveAndFlush(teacher);
        return request;
    }

    public String deleteTeacherById(Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            teacherRepository.flush();
            return "Delete Success !";
        }
        throw new TeacherException("ID has not exist");
    }

    public String changeInfo(Long id, ChangeTeacherRequest request) {
        boolean check=true;
        boolean checkValid=true;
        if ( request.getName()== null && request.getDob()==null && request.getAddress()==null && request.getDepartment()==null) {
            check=false;
        }
        else if (request.getName()== null || request.getDob()==null || request.getAddress()==null || request.getDepartment()==null){
            checkValid=false;
        }

        if (!check) {
            throw new TeacherException("Fill in username, dob and address");
        }
        if (!checkValid) {
            throw new TeacherException("Invalid username, dob or address");
        }

        teacherRepository.getReferenceById(id).setName(request.getNewName());
        teacherRepository.getReferenceById(id).setDob(request.getNewDob());
        teacherRepository.getReferenceById(id).setDepartment(request.getNewDepartment());
        teacherRepository.getReferenceById(id).setAddress(request.getNewAddress());
        teacherRepository.flush();

        return "Change Information Success !";
    }
}
