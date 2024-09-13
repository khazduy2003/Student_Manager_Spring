package student.manager.CRUD.domains.subject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import student.manager.CRUD.application.exceptions.SubjectException;
import student.manager.CRUD.application.exceptions.TeacherException;
import student.manager.CRUD.domains.subject.model.entity.Subject;
import student.manager.CRUD.domains.subject.model.request.ChangeSubjectRequest;
import student.manager.CRUD.domains.subject.model.request.SubjectRequest;
import student.manager.CRUD.domains.subject.repository.SubjectRepository;
import student.manager.CRUD.domains.subject.service.SubjectService;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    public Subject getById(Long id) {
        if (subjectRepository.existsById(id)) {
            return subjectRepository.getReferenceById(id);
        }
        throw new SubjectException("ID has not exist");
    }

    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }
    public SubjectRequest add(SubjectRequest request) {
        boolean check=true;
        boolean checkValid=true;
        if ( request.getName()== null && request.getCredits()==null && request.getTeacherId()==null && request.getClassId()==null) {
            check=false;
        }
        else if (request.getName()== null || request.getCredits()==null || request.getTeacherId()==null || request.getClassId()==null){
            checkValid=false;
        }

        if (!check) {
            throw new TeacherException("Fill in name, credits, teacherId and classId");
        }
        if (!checkValid) {
            throw new TeacherException("Invalid name, credits, teacherId or classId");
        }

        Subject subject = new Subject(request.getName(),request.getCredits(),request.getTeacherId(),request.getClassId());
        subjectRepository.saveAndFlush(subject);
        return request;
    }
    public String deleteById(Long id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
            subjectRepository.flush();
            return "Delete success !";
        }
        throw new SubjectException("ID has not exist");
    }
    public String changeInfo(Long id, ChangeSubjectRequest request) {

        boolean check=true;
        boolean checkValid=true;
        if ( request.getName()== null && request.getCredits()==null && request.getTeacherId()==null && request.getClassId()==null) {
            check=false;
        }
        else if (request.getName()== null || request.getCredits()==null || request.getTeacherId()==null || request.getClassId()==null){
            checkValid=false;
        }

        if (!check) {
            throw new TeacherException("Fill in name, credits, teacherId and classId");
        }
        if (!checkValid) {
            throw new TeacherException("Invalid name, credits, teacherId or classId");
        }

        subjectRepository.getReferenceById(id).setName(request.getNewName());
        subjectRepository.getReferenceById(id).setCredits(request.getNewCredits());
        subjectRepository.getReferenceById(id).setTeacherId(request.getNewTeacherId());
        subjectRepository.getReferenceById(id).setClassId(request.getNewClassId());
        subjectRepository.flush();
        return "Change Information Success !";
    }
}
