package student.manager.CRUD.domains.classes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import student.manager.CRUD.domains.classes.model.entity.Classes;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {
    Classes getClassesById(Long id);
    boolean existsByName(String name);
}
