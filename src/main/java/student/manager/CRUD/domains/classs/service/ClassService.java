package student.manager.CRUD.domains.classs.service;

import student.manager.CRUD.domains.classs.model.entity.Classs;
import student.manager.CRUD.domains.classs.model.request.ChangeClassRequest;
import student.manager.CRUD.domains.classs.model.request.ClassRequest;
import student.manager.CRUD.domains.subject.model.entity.Subject;
import student.manager.CRUD.domains.subject.model.request.ChangeSubjectRequest;
import student.manager.CRUD.domains.subject.model.request.SubjectRequest;

import java.util.List;

public interface ClassService {

    Classs getById(Long id);
    List<Classs> getAll();
    ClassRequest add(ClassRequest request);
    String deleteById(Long id);

    String changeInfo(Long id, ChangeClassRequest request);
}
