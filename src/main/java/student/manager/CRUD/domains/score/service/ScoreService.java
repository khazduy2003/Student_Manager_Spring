package student.manager.CRUD.domains.score.service;

import student.manager.CRUD.domains.score.model.entity.Score;
import student.manager.CRUD.domains.score.model.request.ChangeScoreRequest;
import student.manager.CRUD.domains.score.model.request.ScoreRequest;

import java.util.List;

public interface ScoreService {

    Score getById(Long id);
    List<Score> getAll();
    List<Score> getAllByStudentId(Long id);

    List<Score> getAllBySubjectId(Long id);

    String add(ScoreRequest request);
    String deleteById(Long id);
    String changeInfo(Long id, ChangeScoreRequest request);
}
