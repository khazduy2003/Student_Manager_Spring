package student.manager.CRUD.domains.registersubject.service;

import student.manager.CRUD.domains.registersubject.model.entity.RegisterSubject;
import student.manager.CRUD.domains.registersubject.model.request.RegisterSubjectRequest;

import java.util.List;

public interface RegisterSubjectService {


    RegisterSubject getById(Long id);
    List<RegisterSubject> getAll();
    String register(RegisterSubjectRequest request);
    String deleteById(Long id);

}
