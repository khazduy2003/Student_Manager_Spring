package student.manager.CRUD.domains.classes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import student.manager.CRUD.domains.classes.model.entity.Classes;

@Repository
public interface ClassRepository extends JpaRepository<Classes, Long> {
}
