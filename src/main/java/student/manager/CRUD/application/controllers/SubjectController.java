package student.manager.CRUD.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import student.manager.CRUD.domains.subject.model.entity.Subject;
import student.manager.CRUD.domains.subject.model.request.ChangeSubjectRequest;
import student.manager.CRUD.domains.subject.model.request.SubjectRequest;
import student.manager.CRUD.domains.subject.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    // get by id
    @GetMapping("/{id}")
    public Subject getById(@PathVariable Long id) {
        return subjectService.getById(id);
    }

    // get all
    @GetMapping("/all")
    public List<Subject> getAll() {
        return subjectService.getAll();
    }

    // add
    @PostMapping("/add")
    public SubjectRequest add(@RequestBody SubjectRequest request) {
        return subjectService.add(request);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam Long id) {
        return subjectService.deleteById(id);
    }

    @PutMapping("/changeInformation/{id}")
    public String changeInfo(@PathVariable Long id, @RequestBody ChangeSubjectRequest request) {
        return subjectService.changeInfo(id,request);
    }

}