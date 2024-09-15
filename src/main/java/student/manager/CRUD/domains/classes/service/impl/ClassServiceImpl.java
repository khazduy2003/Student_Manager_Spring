package student.manager.CRUD.domains.classes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.manager.CRUD.application.exceptions.ClassException;
import student.manager.CRUD.application.exceptions.TeacherException;
import student.manager.CRUD.domains.classes.model.entity.Classes;
import student.manager.CRUD.domains.classes.model.request.ChangeClassRequest;
import student.manager.CRUD.domains.classes.model.request.ClassRequest;
import student.manager.CRUD.domains.classes.repository.ClassRepository;
import student.manager.CRUD.domains.classes.service.ClassService;

import java.util.List;

@Service("classService")
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;
    @Override
    public Classes getById(Long id) {
        if (classRepository.existsById(id)) {
            return classRepository.getReferenceById(id);
        }
        throw new ClassException("ID has not exist");
    }

    @Override
    public List<Classes> getAll() {
        return classRepository.findAll();
    }

    @Override
    public ClassRequest add(ClassRequest request) {
        boolean check = (request.getName() != null);
        if (!check) {
            throw new ClassException("Fill in name");
        }
        Classes classes = new Classes(request.getName());
        classRepository.saveAndFlush(classes);
        return request;
    }

    @Override
    public String deleteById(Long id) {
        if (classRepository.existsById(id)) {
            classRepository.deleteById(id);
            classRepository.flush();
            return "Delete Success !";
        }
        throw new ClassException("ID has not exist");
    }

    @Override
    public String changeInfo(Long id, ChangeClassRequest request) {
        if (request.getName()==null) {
            throw new ClassException("Fill in class name");
        }
        classRepository.getReferenceById(id).setName(request.getNewName());
        classRepository.flush();
        return "Change Information Success !";
    }
}
