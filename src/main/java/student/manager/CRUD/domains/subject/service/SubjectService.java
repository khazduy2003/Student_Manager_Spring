package student.manager.CRUD.domains.subject.service;

import student.manager.CRUD.domains.subject.model.entity.Subject;
import student.manager.CRUD.domains.subject.model.request.ChangeSubjectRequest;
import student.manager.CRUD.domains.subject.model.request.SubjectRequest;

import java.util.List;

public interface SubjectService {
    Subject getSubjectById(Long id);
    List<Subject> getAll();
    SubjectRequest add(SubjectRequest request);
    String deleteById(Long id);

    String changeInfo(Long id, ChangeSubjectRequest request);
}
