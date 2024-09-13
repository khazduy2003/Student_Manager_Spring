package student.manager.CRUD.domains.registersubject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.manager.CRUD.domains.registersubject.model.entity.RegisterSubject;

import java.util.List;

public interface RegisterSubjectRepository extends JpaRepository<RegisterSubject,Long> {
    boolean existsByStudentId(Long id);
    List<RegisterSubject> findRegisterSubjectByStudentId(Long studentId);
}
