package student.manager.CRUD.domains.registersubject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.manager.CRUD.application.exceptions.RegisterSubjectException;
import student.manager.CRUD.application.exceptions.StudentException;
import student.manager.CRUD.domains.registersubject.model.entity.RegisterSubject;
import student.manager.CRUD.domains.registersubject.model.request.RegisterSubjectRequest;
import student.manager.CRUD.domains.registersubject.repository.RegisterSubjectRepository;
import student.manager.CRUD.domains.registersubject.service.RegisterSubjectService;
import student.manager.CRUD.domains.student.model.entity.Student;
import student.manager.CRUD.domains.student.repository.StudentRepository;
import student.manager.CRUD.domains.subject.model.entity.Subject;
import student.manager.CRUD.domains.subject.repository.SubjectRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegisterSubjectServiceImpl implements RegisterSubjectService {

    @Autowired
    private RegisterSubjectRepository registerSubjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public RegisterSubject getById(Long id) {
        boolean check = registerSubjectRepository.existsById(id);
        if (check) {
            return registerSubjectRepository.getRegisterSubjectById(id);
        }
        throw new RegisterSubjectException("ID has not exist");
    }

    @Override
    public List<RegisterSubject> getAll() {
        return registerSubjectRepository.findAll();
    }
    @Override
    public String register(RegisterSubjectRequest request) {

        boolean checkStudent;
        boolean checkSubject = false;
        if (request.getSubjectId()==null && request.getStudentId()==null) {
            throw new RegisterSubjectException("Fill in SubjectID and StudentID");
        }
        if (request.getStudentId()==null || request.getSubjectId()==null) {
            throw new RegisterSubjectException("Invalid SubjectID or StudentID");
        }

        if (!studentRepository.existsById(request.getStudentId())
                || !subjectRepository.existsById(request.getSubjectId())) {
            throw new StudentException("Student ID or Subject ID has not exist");
        }

        checkStudent = registerSubjectRepository.existsByStudentId(request.getStudentId());

        if (checkStudent) {
            List<RegisterSubject> registerSubjectListByStudentId = registerSubjectRepository.getAllByStudentId(request.getStudentId());
            for (RegisterSubject registerSubject : registerSubjectListByStudentId) {
                if (registerSubject.getSubject().getId().equals(request.getSubjectId())) {
                    checkSubject = true;
                    break;
                }
            }
            if (checkSubject) {
                throw new RegisterSubjectException("Duplicate RegisterSubject");
            }
        }

        Student student = studentRepository.getStudentById(request.getStudentId());
        Subject subject = subjectRepository.getSubjectById(request.getSubjectId());
        LocalDate today = LocalDate.now();
        RegisterSubject registerSubject = new RegisterSubject(student,subject,today);
        registerSubjectRepository.saveAndFlush(registerSubject);
        return "Registered Success !";
    }

    @Override
    public String deleteById(Long id) {
        boolean check = registerSubjectRepository.existsById(id);
        if (check) {
            registerSubjectRepository.deleteById(id);
            registerSubjectRepository.flush();
            return "Deleted Success !";
        }
        throw new RegisterSubjectException("ID has not exist");
    }

}
