package student.manager.CRUD.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import student.manager.CRUD.domains.student.model.entity.Student;
import student.manager.CRUD.domains.student.model.request.ChangeStudentRequest;
import student.manager.CRUD.domains.student.model.request.StudentRequest;
import student.manager.CRUD.domains.student.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    // get student by id
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // get all of student
    @GetMapping("/all")
    public List<Student> getAllOfStudent() {
        return studentService.getAllOfStudent();
    }

    @PostMapping("/add")
    public StudentRequest addStudent(@RequestBody StudentRequest request) {
        return studentService.addStudent(request);
    }

    @DeleteMapping("/delete")
    public String deleteStudent(@RequestParam Long id) {
        return studentService.deleteStudent(id);
    }

    @PutMapping("/changeInformation/{id}")
    public String changeInfo(@PathVariable Long id, @RequestBody ChangeStudentRequest request) {
        return studentService.changeInfo(id,request);
    }
}
