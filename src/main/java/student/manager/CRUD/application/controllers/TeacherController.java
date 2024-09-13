package student.manager.CRUD.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import student.manager.CRUD.domains.teacher.model.entity.Teacher;
import student.manager.CRUD.domains.teacher.model.request.ChangeTeacherRequest;
import student.manager.CRUD.domains.teacher.model.request.TeacherRequest;
import student.manager.CRUD.domains.teacher.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // get teacher by id
    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable Long id) {
        return teacherService.getTeacherById(id);
    }

    // get all of student
    @GetMapping("/all")
    public List<Teacher> getAllOfTeacher() {
        return teacherService.getAllOfTeacher();
    }

    @PostMapping("/add")
    public TeacherRequest addTeacher(@RequestBody TeacherRequest request) {
        return teacherService.addTeacher(request);
    }

    @DeleteMapping("/delete")
    public String deleteTeacher(@RequestParam Long id) {
        return teacherService.deleteTeacherById(id);
    }

    @PutMapping("/changeInformation/{id}")
    public String changeInfo(@PathVariable Long id, @RequestBody ChangeTeacherRequest request) {
        return teacherService.changeInfo(id,request);
    }
}
