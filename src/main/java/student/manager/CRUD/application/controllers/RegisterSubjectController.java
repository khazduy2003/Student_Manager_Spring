package student.manager.CRUD.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import student.manager.CRUD.domains.registersubject.model.request.RegisterSubjectRequest;
import student.manager.CRUD.domains.registersubject.service.RegisterSubjectService;

@RestController
@RequestMapping("/register")
public class RegisterSubjectController {

    @Autowired
    private RegisterSubjectService registerSubjectService;

    @PostMapping
    public String register(@RequestBody RegisterSubjectRequest request) {
        return registerSubjectService.register(request);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        return registerSubjectService.deleteById(id);
    }
}
