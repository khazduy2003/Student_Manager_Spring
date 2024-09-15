package student.manager.CRUD.domains.classes.service;

import org.springframework.stereotype.Component;
import student.manager.CRUD.domains.classes.model.entity.Classes;
import student.manager.CRUD.domains.classes.model.request.ChangeClassRequest;
import student.manager.CRUD.domains.classes.model.request.ClassRequest;

import java.util.List;

@Component
public interface ClassService {

    Classes getById(Long id);
    List<Classes> getAll();
    ClassRequest add(ClassRequest request);
    String deleteById(Long id);
    String changeInfo(Long id, ChangeClassRequest request);
}
