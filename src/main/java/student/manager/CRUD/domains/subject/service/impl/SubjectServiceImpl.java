package student.manager.CRUD.domains.subject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.manager.CRUD.application.exceptions.StudentException;
import student.manager.CRUD.application.exceptions.SubjectException;
import student.manager.CRUD.application.exceptions.TeacherException;
import student.manager.CRUD.domains.classes.model.entity.Classes;
import student.manager.CRUD.domains.classes.repository.ClassesRepository;
import student.manager.CRUD.domains.subject.model.entity.Subject;
import student.manager.CRUD.domains.subject.model.request.ChangeSubjectRequest;
import student.manager.CRUD.domains.subject.model.request.SubjectRequest;
import student.manager.CRUD.domains.subject.repository.SubjectRepository;
import student.manager.CRUD.domains.subject.service.SubjectService;
import student.manager.CRUD.domains.teacher.model.entity.Teacher;
import student.manager.CRUD.domains.teacher.repository.TeacherRepository;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ClassesRepository classesRepository;

    public Subject getSubjectById(Long id) {

        boolean check = subjectRepository.existsById(id);
        if (check) {
            return subjectRepository.getSubjectById(id);
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

        if (subjectRepository.existsByName(request.getName())) {
            throw new SubjectException("Duplicated name of subject");
        }

        Classes classes = classesRepository.getClassesById(request.getClassId());
        Teacher teacher = teacherRepository.getTeacherById(request.getTeacherId());

        Subject subject = new Subject(request.getName(), request.getCredits(), classes, teacher);
        subjectRepository.saveAndFlush(subject);
        return request;
    }
    public String deleteById(Long id) {
        boolean check = subjectRepository.existsById(id);
        if (check) {
            subjectRepository.deleteById(id);
            subjectRepository.flush();
            return "Delete success !";
        }
        throw new SubjectException("ID has not exist");
    }
    public String changeInfo(Long id, ChangeSubjectRequest request) {

        boolean check = subjectRepository.existsById(id);

        if (!check) {
            throw new StudentException("ID has not exist");
        }

        Subject subject = subjectRepository.getSubjectById(id);

        if (request.getName()== null && request.getCredits()==null && request.getTeacherId()==null && request.getClassId()==null) {
            throw new StudentException("Fill in name, credits, teacherID or classID");
        }
        if (request.getNewName()== null && request.getNewCredits()==null && request.getNewTeacherId()==null && request.getNewClassId()==null) {
            throw new StudentException("Invalid new information");
        }
        if ((request.getName()!=null && request.getNewName()==null)
                || (request.getCredits()!=null && request.getNewCredits()==null)
                || (request.getTeacherId()!=null && request.getNewTeacherId()==null)
                || (request.getClassId()!=null && request.getNewClassId()==null) ) {
            throw new StudentException("Must fill in new name, new credits, new teacherID or newClassID");
        }

        if ((request.getNewName()!=null && request.getName()==null)
                || (request.getNewCredits()!=null && request.getCredits()==null)
                || (request.getNewTeacherId()!=null && request.getTeacherId()==null)
                || (request.getNewClassId()!=null && request.getClassId()==null) ) {
            throw new StudentException("Must fill in name, credits, teacherID or classID");
        }

        if ( (request.getName()!=null && !request.getName().equals(subject.getName()))
                || (request.getCredits()!=null && !request.getCredits().equals(subject.getCredits()))
                || (request.getTeacherId()!=null && !request.getTeacherId().equals(subject.getTeacher().getId()))
                || (request.getClassId()!=null && !request.getClassId().equals(subject.getClasses().getId()))) {
            throw new StudentException("Incorrect name, day of birth or address");
        }

        if ( (request.getName()!=null && request.getName().equals(request.getNewName()))
                || (request.getCredits()!=null && request.getCredits().equals(request.getNewCredits()))
                || (request.getTeacherId()!=null && request.getTeacherId().equals(request.getNewTeacherId()))
                || (request.getClassId()!=null && request.getClassId().equals(request.getNewClassId())) ) {
            throw new StudentException("Duplicate new name, new day of birth or new address");
        }

        boolean checkExits=false;
        if (request.getTeacherId()!=null && request.getClassId()!=null) {
            checkExits=subjectRepository.existsByTeacherIdAndClassesId(request.getNewTeacherId(), request.getNewClassId());
        }
        if (request.getTeacherId()!=null && request.getClassId()==null) {
            checkExits=subjectRepository.existsByTeacherIdAndClassesId(request.getNewTeacherId(),subject.getClasses().getId());
        }
        if (request.getTeacherId()==null && request.getClassId()!=null) {
            checkExits=subjectRepository.existsByTeacherIdAndClassesId(subject.getTeacher().getId(),request.getNewClassId());
        }

        if (checkExits) {
            throw new SubjectException("This teacher is teaching in this class ");
        }

        if (request.getNewName()!=null) {
            subject.setName(request.getNewName());
        }
        if (request.getCredits()!=null) {
            subject.setCredits(request.getNewCredits());
        }
        if (request.getTeacherId()!=null) {
            Teacher teacher = teacherRepository.getTeacherById(id);
            subject.setTeacher(teacher);
        }
        if (request.getClassId()!=null) {
            Classes classes = classesRepository.getClassesById(id);
            subject.setClasses(classes);
        }
        subjectRepository.saveAndFlush(subject);

        return "Change Information Success";
    }
}
