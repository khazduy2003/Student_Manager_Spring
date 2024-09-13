package student.manager.CRUD.domains.classs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.manager.CRUD.application.exceptions.ClassException;
import student.manager.CRUD.application.exceptions.TeacherException;
import student.manager.CRUD.domains.classs.model.entity.Classs;
import student.manager.CRUD.domains.classs.model.request.ChangeClassRequest;
import student.manager.CRUD.domains.classs.model.request.ClassRequest;
import student.manager.CRUD.domains.classs.repository.ClassRepository;
import student.manager.CRUD.domains.classs.service.ClassService;
import student.manager.CRUD.domains.subject.model.request.SubjectRequest;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;
    @Override
    public Classs getById(Long id) {
        if (classRepository.existsById(id)) {
            return classRepository.getReferenceById(id);
        }
        throw new ClassException("ID has not exist");
    }

    @Override
    public List<Classs> getAll() {
        return classRepository.findAll();
    }

    @Override
    public ClassRequest add(ClassRequest request) {
        boolean check = (request.getName() != null);
        if (!check) {
            throw new TeacherException("Fill in name");
        }
        Classs classs = new Classs(request.getName());
        classRepository.saveAndFlush(classs);
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
