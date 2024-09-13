package student.manager.CRUD.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import student.manager.CRUD.domains.classs.model.entity.Classs;
import student.manager.CRUD.domains.classs.model.request.ChangeClassRequest;
import student.manager.CRUD.domains.classs.model.request.ClassRequest;
import student.manager.CRUD.domains.classs.service.ClassService;
import student.manager.CRUD.domains.subject.model.entity.Subject;
import student.manager.CRUD.domains.subject.model.request.ChangeSubjectRequest;
import student.manager.CRUD.domains.subject.model.request.SubjectRequest;
import student.manager.CRUD.domains.subject.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClasssController {

    @Autowired
    private ClassService classService;

    // get by id
    @GetMapping("/{id}")
    public Classs getById(@PathVariable Long id) {
        return classService.getById(id);
    }

    // get all
    @GetMapping("/all")
    public List<Classs> getAll() {
        return classService.getAll();
    }

    // add
    @PostMapping("/add")
    public ClassRequest add(@RequestBody ClassRequest request) {
        return classService.add(request);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam Long id) {
        return classService.deleteById(id);
    }

    @PutMapping("/changeInformation/{id}")
    public String changeInfo(@PathVariable Long id, @RequestBody ChangeClassRequest request) {
        return classService.changeInfo(id,request);
    }

}
