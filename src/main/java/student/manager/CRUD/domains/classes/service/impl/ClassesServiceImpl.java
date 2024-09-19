package student.manager.CRUD.domains.classes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.manager.CRUD.application.exceptions.ClassException;
import student.manager.CRUD.domains.classes.model.entity.Classes;
import student.manager.CRUD.domains.classes.model.request.ChangeClassRequest;
import student.manager.CRUD.domains.classes.model.request.ClassRequest;
import student.manager.CRUD.domains.classes.repository.ClassesRepository;
import student.manager.CRUD.domains.classes.service.ClassesService;

import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesRepository classesRepository;
    @Override
    public Classes getById(Long id) {
        boolean check = classesRepository.existsById(id);
        if (check) {
            return classesRepository.getClassesById(id);
        }
        throw new ClassException("ID has not exist");
    }

    @Override
    public List<Classes> getAll() {
        return classesRepository.findAll();
    }

    @Override
    public ClassRequest add(ClassRequest request) {

        boolean check = request.getName() == null;

        if (check) {
            throw new ClassException("Fill in className");
        }
        if (classesRepository.existsByName(request.getName())) {
            throw new ClassException("Duplicated className");
        }
        Classes classes = new Classes(request.getName());
        classesRepository.saveAndFlush(classes);
        return request;
    }

    @Override
    public String deleteById(Long id) {

        boolean check = classesRepository.existsById(id);
        if (check) {
            classesRepository.deleteById(id);
            classesRepository.flush();
            return "Delete Success !";
        }
        throw new ClassException("ID has not exist");
    }

    @Override
    public String changeInfo(Long id, ChangeClassRequest request) {

        boolean check = classesRepository.existsById(id);

        if (!check) {
            throw new ClassException("ID has not exist");
        }



        if (request.getName()==null && request.getNewName()==null) {
            throw new ClassException("Fill in className and newClassName");
        }
        if (request.getName()==null || request.getNewName()==null) {
            throw new ClassException("Invalid className or newClassName");
        }

        Classes classes = classesRepository.getClassesById(id);

        if (!request.getName().equals(classes.getName())) {
            throw new ClassException("Incorrect className");
        }

        if (request.getName().equals(request.getNewName())) {
            throw new ClassException("Duplicate className");
        }

        classes.setName(request.getNewName());
        classesRepository.saveAndFlush(classes);
        return "Change Information Success !";
    }
}