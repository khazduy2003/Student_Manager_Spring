package student.manager.CRUD.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import student.manager.CRUD.domains.score.model.entity.Score;
import student.manager.CRUD.domains.score.model.request.ChangeScoreRequest;
import student.manager.CRUD.domains.score.model.request.ScoreRequest;
import student.manager.CRUD.domains.score.service.ScoreService;
import student.manager.CRUD.domains.subject.model.entity.Subject;
import student.manager.CRUD.domains.subject.model.request.ChangeSubjectRequest;
import student.manager.CRUD.domains.subject.model.request.SubjectRequest;
import student.manager.CRUD.domains.subject.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    // get all score by student_id
    @GetMapping("/all/student/{id}")
    public List<Score> getAllByStudentId(@PathVariable Long id) {
        return scoreService.getAllByStudentId(id);
    }

    // get all score by subject_id
    @GetMapping("/all/subject/{id}")
    public List<Score> getAllBySubjectId(@PathVariable Long id) {
        return scoreService.getAllBySubjectId(id);
    }

    // add
    @PostMapping("/add")
    public Score add(@RequestBody ScoreRequest request) {
        return scoreService.add(request);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam Long studentId, Long subjectId) {
        return scoreService.deleteById(studentId,subjectId);
    }

    @PutMapping("/changeInformation/{id}")
    public String changeInfo(@PathVariable Long id, @RequestBody ChangeScoreRequest request) {
        return scoreService.changeInfo(id,request);
    }
}
