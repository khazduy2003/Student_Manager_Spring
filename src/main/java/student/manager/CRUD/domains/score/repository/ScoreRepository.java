package student.manager.CRUD.domains.score.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.manager.CRUD.domains.score.model.entity.Score;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score,Long> {

    boolean existsByStudentId(Long id);
    boolean existsBySubjectId(Long id);
    List<Score> findAllByStudentId(Long id);
    List<Score> findAllBySubjectId(Long id);
    Score findByStudentIdAndSubjectId(Long studentId, Long subjectId);
}
