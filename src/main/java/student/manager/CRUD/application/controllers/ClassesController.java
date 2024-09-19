package student.manager.CRUD.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import student.manager.CRUD.domains.classes.model.entity.Classes;
import student.manager.CRUD.domains.classes.model.request.ChangeClassRequest;
import student.manager.CRUD.domains.classes.model.request.ClassRequest;
import student.manager.CRUD.domains.classes.service.ClassesService;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    // get by id
    @GetMapping("/{id}")
    public Classes getById(@PathVariable Long id) {
        return classesService.getById(id);
    }

    // get all
    @GetMapping("/all")
    public List<Classes> getAll() {
        return classesService.getAll();
    }

    // add
    @PostMapping("/add")
    public ClassRequest add(@RequestBody ClassRequest request) {
        return classesService.add(request);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam Long id) {
        return classesService.deleteById(id);
    }

    @PutMapping("/changeInformation/{id}")
    public String changeInfo(@PathVariable Long id, @RequestBody ChangeClassRequest request) {
        return classesService.changeInfo(id,request);
    }

}
