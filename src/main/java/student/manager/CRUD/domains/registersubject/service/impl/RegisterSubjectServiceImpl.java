package student.manager.CRUD.domains.registersubject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.manager.CRUD.application.exceptions.RegisterSubjectException;
import student.manager.CRUD.domains.registersubject.model.entity.RegisterSubject;
import student.manager.CRUD.domains.registersubject.model.request.RegisterSubjectRequest;
import student.manager.CRUD.domains.registersubject.repository.RegisterSubjectRepository;
import student.manager.CRUD.domains.registersubject.service.RegisterSubjectService;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegisterSubjectServiceImpl implements RegisterSubjectService {

    @Autowired
    private RegisterSubjectRepository registerSubjectRepository;

    @Override
    public String register(RegisterSubjectRequest request) {
        boolean checkStudent=true;
        boolean checkSubject=true;
        if (request.getSubjectId()==null && request.getStudentId()==null) {
            throw new RegisterSubjectException("Fill in SubjectID and StudentID");
        }
        if (request.getStudentId()==null || request.getSubjectId()==null) {
            throw new RegisterSubjectException("Invalid SubjectID or StudentID");
        }

        if (registerSubjectRepository.existsByStudentId(request.getStudentId())) {
            checkStudent=false;
        }
        if (!checkStudent) {
            List<RegisterSubject> tempList = registerSubjectRepository.findRegisterSubjectByStudentId(request.getStudentId());
            for (RegisterSubject registerSubject : tempList) {
                if (registerSubject.getSubjectId().equals(request.getSubjectId())) {
                    checkSubject = false;
                    break;
                }
            }
        }
        if (!checkSubject) {
            throw new RegisterSubjectException("Duplicate RegisterSubject");
        }

        LocalDate today = LocalDate.now();
        RegisterSubject registerSubject = new RegisterSubject(request.getStudentId(),request.getSubjectId(),today);
        registerSubjectRepository.saveAndFlush(registerSubject);
        return "Registered Success !";
    }

    @Override
    public String deleteById(Long id) {
        if (registerSubjectRepository.existsById(id)) {
            registerSubjectRepository.deleteById(id);
            registerSubjectRepository.flush();
            return "Deleted Success !";
        }
        throw new RegisterSubjectException("ID has not exist");
    }

}
