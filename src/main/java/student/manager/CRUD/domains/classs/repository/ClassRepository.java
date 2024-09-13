package student.manager.CRUD.domains.classs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import student.manager.CRUD.domains.classs.model.entity.Classs;

@Repository
public interface ClassRepository extends JpaRepository<Classs,Long> {
}
