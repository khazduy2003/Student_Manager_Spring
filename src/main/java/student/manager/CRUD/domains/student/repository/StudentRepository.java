package student.manager.CRUD.domains.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import student.manager.CRUD.domains.student.model.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Student getStudentById(Long id);
}
