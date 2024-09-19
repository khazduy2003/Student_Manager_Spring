package student.manager.CRUD.domains.subject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.manager.CRUD.domains.subject.model.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    Subject getSubjectById(Long id);
    boolean existsByName(String name);
    boolean existsByTeacherIdAndClassesId(Long teacherId, Long subjectId);
}
