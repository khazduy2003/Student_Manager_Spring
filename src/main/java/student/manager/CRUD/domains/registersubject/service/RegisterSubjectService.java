package student.manager.CRUD.domains.registersubject.service;

import student.manager.CRUD.domains.registersubject.model.request.RegisterSubjectRequest;

public interface RegisterSubjectService {
    String register(RegisterSubjectRequest request);
    String deleteById(Long id);
}
