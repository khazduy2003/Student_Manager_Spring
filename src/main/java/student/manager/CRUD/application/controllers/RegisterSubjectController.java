package student.manager.CRUD.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import student.manager.CRUD.domains.registersubject.model.entity.RegisterSubject;
import student.manager.CRUD.domains.registersubject.model.request.RegisterSubjectRequest;
import student.manager.CRUD.domains.registersubject.service.RegisterSubjectService;

import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterSubjectController {

    @Autowired
    private RegisterSubjectService registerSubjectService;

    @GetMapping("/{id}")
    public RegisterSubject getById(@PathVariable  Long id) {
        return registerSubjectService.getById(id);
    }

    @GetMapping("/all")
    public List<RegisterSubject> getAll() {
        return registerSubjectService.getAll();
    }

    @PostMapping("/add")
    public String register(@RequestBody RegisterSubjectRequest request) {
        return registerSubjectService.register(request);
    }

    @DeleteMapping("/delete")
    public String deleteById(@RequestParam Long id) {
        return registerSubjectService.deleteById(id);
    }
}
