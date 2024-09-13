package student.manager.CRUD.domains.teacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student.manager.CRUD.domains.teacher.model.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {

}
