package student.manager.CRUD.domains.score.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.manager.CRUD.application.exceptions.RegisterSubjectException;
import student.manager.CRUD.application.exceptions.ScoreException;
import student.manager.CRUD.application.exceptions.StudentException;
import student.manager.CRUD.domains.score.model.entity.Score;
import student.manager.CRUD.domains.score.model.request.ChangeScoreRequest;
import student.manager.CRUD.domains.score.model.request.ScoreRequest;
import student.manager.CRUD.domains.score.repository.ScoreRepository;
import student.manager.CRUD.domains.score.service.ScoreService;
import student.manager.CRUD.domains.student.model.entity.Student;
import student.manager.CRUD.domains.student.repository.StudentRepository;
import student.manager.CRUD.domains.subject.model.entity.Subject;
import student.manager.CRUD.domains.subject.repository.SubjectRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Score getById(Long id) {

        boolean check = scoreRepository.existsById(id);
        if (check) {
            return scoreRepository.getScoreById(id);
        }
        throw new ScoreException("ID has not exist");
    }
    @Override
    public List<Score> getAll() {
        return scoreRepository.findAll();
    }
    @Override
    public List<Score> getAllByStudentId(Long id) {
        boolean check = scoreRepository.existsByStudentId(id);
        if (check) {
            return scoreRepository.findAllByStudentId(id);
        }
        throw new ScoreException("StudentID has not exist !");
    }

    @Override
    public List<Score> getAllBySubjectId(Long id) {
        boolean check = scoreRepository.existsBySubjectId(id);
        if (check) {
            return scoreRepository.findAllBySubjectId(id);
        }
        throw new ScoreException("SubjectID has not exist");
    }

    @Override
    public String add(ScoreRequest request) {

        boolean checkStudent;
        boolean checkSubject = false;
        if (request.getSubjectId()==null && request.getStudentId()==null && request.getScore()==null) {
            throw new ScoreException("Fill in SubjectID, StudentID and Socre");
        }
        if (request.getStudentId()==null || request.getSubjectId()==null || request.getScore()==null) {
            throw new ScoreException("Invalid SubjectID StudentID or Score");
        }

        if (!studentRepository.existsById(request.getStudentId())
                || !subjectRepository.existsById(request.getSubjectId())) {
            throw new StudentException("Student ID or Subject ID has not exist");
        }

        checkStudent = scoreRepository.existsByStudentId(request.getStudentId());

        if (checkStudent) {
            List<Score> scoreRepositoryAllByStudentId = scoreRepository.findAllByStudentId(request.getStudentId());
            for (Score score : scoreRepositoryAllByStudentId) {
                if (score.getSubject().getId().equals(request.getSubjectId())) {
                    checkSubject = true;
                    break;
                }
            }
            if (checkSubject) {
                throw new RegisterSubjectException("Duplicate StudentId and SubjectId");
            }
        }

        Student student = studentRepository.getStudentById(request.getStudentId());
        Subject subject = subjectRepository.getSubjectById(request.getSubjectId());
        LocalDate today = LocalDate.now();
        Score score = new Score(student,subject,request.getScore(),today);
        scoreRepository.saveAndFlush(score);
        return "Registered Success !";
    }

    @Override
    public String deleteById(Long id) {

        boolean check = scoreRepository.existsById(id);
        if (check) {
            scoreRepository.deleteById(id);
            scoreRepository.flush();
            return "Deleted Success";
        }
        else  {
            throw new ScoreException("ID has not exits");
        }
    }

    @Override
    public String changeInfo(Long id, ChangeScoreRequest request) {

        boolean check = scoreRepository.existsById(id);
        if (!check) {
            throw new ScoreException("ID has not exist");
        }

        Score score = scoreRepository.getScoreById(id);

        if (request.getScore()== null && request.getSubjectId()==null && request.getStudentId()==null) {
            throw new StudentException("Fill in studentID, subjectID or Score");
        }
        if (request.getNewScore()== null && request.getNewSubjectId()==null && request.getNewStudentId()==null) {
            throw new StudentException("Fill in NEW studentID, subjectID or Score");
        }
        if ((request.getStudentId()!=null && request.getNewStudentId()==null)
                || (request.getSubjectId()!=null && request.getNewSubjectId()==null)
                || (request.getScore()!=null && request.getNewScore()==null) ) {
            throw new StudentException("Must fill in new studentID, new subjectID or new score");
        }

        if ((request.getNewStudentId()!=null && request.getStudentId()==null)
                || (request.getNewSubjectId()!=null && request.getSubjectId()==null)
                || (request.getNewScore()!=null && request.getScore()==null) ) {
            throw new StudentException("Must fill in studentID, subjectID or score");
        }

        if ( (request.getStudentId()!=null && !request.getStudentId().equals(score.getStudent().getId()))
                || (request.getSubjectId()!=null && !request.getSubjectId().equals(score.getSubject().getId()))
                || (request.getScore()!=null && !request.getScore().equals(score.getScore())) ) {
            throw new StudentException("Incorrect studentID, subjectID or score");
        }


        if ( (request.getScore()!=null && request.getScore().equals(request.getNewScore()))
                || (request.getStudentId()!=null && request.getStudentId().equals(request.getNewStudentId()))
                || (request.getSubjectId()!=null && request.getSubjectId().equals(request.getNewSubjectId())) ) {
            throw new StudentException("Duplicate NEW StudentId, SubjectId or score");
        }

        boolean checkExist=false;
        if (request.getNewStudentId()!=null && request.getNewSubjectId()!=null) {
            checkExist = scoreRepository.existsByStudentIdAndSubjectId(request.getNewStudentId(),request.getNewSubjectId());
        }
        if (request.getNewStudentId() != null && request.getNewSubjectId()==null) {
            checkExist=scoreRepository.existsByStudentIdAndSubjectId(request.getNewStudentId(),score.getSubject().getId());
        }
        if (request.getNewStudentId() == null && request.getNewSubjectId()!=null) {
            checkExist=scoreRepository.existsByStudentIdAndSubjectId(score.getStudent().getId(), request.getNewSubjectId());
        }

        if (checkExist) {
            throw new ScoreException("This student has score of this subject");
        }

        if (request.getStudentId()!=null) {
            Student student = studentRepository.getStudentById(request.getNewStudentId());
            score.setStudent(student);
        }
        if (request.getSubjectId()!=null) {
            Subject subject = subjectRepository.getSubjectById(request.getNewSubjectId());
            score.setSubject(subject);
        }
        if (request.getScore()!=null) {
            score.setScore(request.getNewScore());
        }

        scoreRepository.saveAndFlush(score);

        return "Change Information Success !";
    }
}
