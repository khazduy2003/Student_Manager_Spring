package student.manager.CRUD.domains.score.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.manager.CRUD.application.exceptions.RegisterSubjectException;
import student.manager.CRUD.application.exceptions.ScoreException;
import student.manager.CRUD.application.exceptions.TeacherException;
import student.manager.CRUD.domains.registersubject.model.entity.RegisterSubject;
import student.manager.CRUD.domains.score.model.entity.Score;
import student.manager.CRUD.domains.score.model.request.ChangeScoreRequest;
import student.manager.CRUD.domains.score.model.request.ScoreRequest;
import student.manager.CRUD.domains.score.repository.ScoreRepository;
import student.manager.CRUD.domains.score.service.ScoreService;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public List<Score> getAllByStudentId(Long id) {
        if (scoreRepository.existsByStudentId(id)) {
            return scoreRepository.findAllByStudentId(id);
        }
        throw new ScoreException("StudentID has not exist !");
    }

    @Override
    public List<Score> getAllBySubjectId(Long id) {
        if (scoreRepository.existsBySubjectId(id)) {
            return scoreRepository.findAllBySubjectId(id);
        }
        throw new ScoreException("SubjectID has not exist");
    }

    @Override
    public Score add(ScoreRequest request) {
        boolean checkStudent=true;
        boolean checkSubject=true;
        if (request.getSubjectId()==null && request.getStudentId()==null && request.getScore()==null) {
            throw new RegisterSubjectException("Fill in SubjectID and StudentID");
        }
        if (request.getStudentId()==null || request.getSubjectId()==null || request.getScore()==null) {
            throw new RegisterSubjectException("Invalid SubjectID or StudentID");
        }

        if (scoreRepository.existsByStudentId(request.getStudentId())) {
            checkStudent=false;
        }
        if (!checkStudent) {
            List<Score> tempList = scoreRepository.findAllByStudentId(request.getStudentId());
            for (Score score : tempList) {
                if (score.getSubjectId().equals(request.getSubjectId())) {
                    checkSubject = false;
                    break;
                }
            }
        }
        if (!checkSubject) {
            throw new RegisterSubjectException("Duplicate StudentID and SubjectID");
        }

        LocalDate today = LocalDate.now();
        Score score = new Score(request.getStudentId(),request.getSubjectId(),request.getScore(),today);
        scoreRepository.saveAndFlush(score);
        return score;
    }

    @Override
    public String deleteById(Long studentId, Long subjectId) {
        boolean check=false;
        if (studentId==null && subjectId==null) {
            throw new ScoreException("Fill in StudentID and SubjectID");
        }
        if (studentId==null || subjectId==null) {
            throw new ScoreException("Invalid StudentID or SubjectID");
        }

        for (Score score : scoreRepository.findAll()) {
            if (score.getStudentId().equals(studentId) && score.getSubjectId().equals(subjectId)) {
                check=true;
                break;
            }
        }
        if (!check) {
            throw new ScoreException("Incorrect StudentID or SubjectID");
        }

        Score score = scoreRepository.findByStudentIdAndSubjectId(studentId,subjectId);
        scoreRepository.delete(score);
        scoreRepository.flush();
        return "Deleted Success";
    }

    @Override
    public String changeInfo(Long id, ChangeScoreRequest request) {
        boolean check=true;
        boolean checkValid=true;
        if ( request.getStudentId()== null && request.getSubjectId()==null && request.getScore()==null) {
            check=false;
        }
        else if (request.getStudentId()== null || request.getSubjectId()==null || request.getScore()==null){
            checkValid=false;
        }

        if (!check) {
            throw new TeacherException("Fill in StudentID, SubjectID and Score");
        }
        if (!checkValid) {
            throw new TeacherException("Invalid StudentID, SubjectID or Score");
        }

        scoreRepository.getReferenceById(id).setStudentId(request.getNewStudentId());
        scoreRepository.getReferenceById(id).setSubjectId(request.getNewSubjectId());
        scoreRepository.getReferenceById(id).setScore(request.getNewScore());
        scoreRepository.flush();
        return "Changed Information Success";
    }
}
