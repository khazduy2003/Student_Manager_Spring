package student.manager.CRUD.domains.teacher.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.manager.CRUD.application.exceptions.StudentException;
import student.manager.CRUD.application.exceptions.TeacherException;
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

        boolean check = teacherRepository.existsById(id);
        if (check) {
            return teacherRepository.getTeacherById(id);
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

        boolean check = teacherRepository.existsById(id);
        if (check) {
            teacherRepository.deleteById(id);
            teacherRepository.flush();
            return "Delete Success !";
        }
        throw new TeacherException("ID has not exist");
    }

    public String changeInfo(Long id, ChangeTeacherRequest request) {

        boolean check = teacherRepository.existsById(id);
        if (!check) {
            throw new TeacherException("ID has not exist");
        }

        Teacher teacher = teacherRepository.getTeacherById(id);

        if (request.getName()== null && request.getDob()==null && request.getAddress()==null && request.getDepartment()==null) {
            throw new StudentException("Fill in username, dob or address");
        }
        if (request.getNewName()== null && request.getNewDob()==null && request.getNewAddress()==null && request.getDepartment()==null) {
            throw new StudentException("Invalid new information");
        }
        if ((request.getName()!=null && request.getNewName()==null)
                || (request.getDob()!=null && request.getNewDob()==null)
                || (request.getAddress()!=null && request.getNewAddress()==null)
                || (request.getDepartment()!=null && request.getNewDepartment()==null) ) {
            throw new StudentException("Must fill in new name, new day of birth, new address or new department");
        }

        if ((request.getNewName()!=null && request.getName()==null)
                || (request.getNewDob()!=null && request.getDob()==null)
                || (request.getNewAddress()!=null && request.getAddress()==null) ) {
            throw new StudentException("Must fill in name, day of birth or address");
        }

        if ( (request.getName()!=null && !request.getName().equals(teacher.getName()))
                || (request.getDob()!=null && !request.getDob().equals(teacher.getDob()))
                || (request.getAddress()!=null && !request.getAddress().equals(teacher.getAddress()))
                || (request.getDepartment()!=null && !request.getDepartment().equals(teacher.getDepartment()))) {
            throw new StudentException("Incorrect name, day of birth address or department");
        }

        if ( (request.getName()!=null && request.getNewName().equals(request.getName()))
                || (request.getDob()!=null && request.getDob().equals(request.getNewDob()))
                || (request.getAddress()!=null && request.getAddress().equals(request.getNewAddress()))
                || (request.getDepartment()!=null && request.getDepartment().equals(request.getNewDepartment()))) {
            throw new StudentException("Duplicate new name, new day of birth, new address or new department");
        }

        if (request.getNewName()!=null) {
            teacher.setName(request.getNewName());
        }
        if (request.getNewDob()!=null) {
            teacher.setDob(request.getNewDob());
        }
        if (request.getNewAddress()!=null) {
            teacher.setAddress(request.getNewAddress());
        }
        if (request.getNewDepartment()!=null) {
            teacher.setAddress(request.getNewDepartment());
        }

        teacherRepository.saveAndFlush(teacher);

        return "Change Information Success !";

    }
}
