package student.manager.CRUD.domains.subject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.manager.CRUD.domains.subject.model.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
