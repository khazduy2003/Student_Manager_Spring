package student.manager.CRUD.domains.registersubject.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import student.manager.CRUD.application.base.BaseEntity;
import student.manager.CRUD.domains.student.model.entity.Student;
import student.manager.CRUD.domains.subject.model.entity.Subject;
import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "registersubject")
@Data
public class RegisterSubject extends BaseEntity {

    @Column(name = "register_date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false, referencedColumnName = "student_id")
    @JsonBackReference
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false, referencedColumnName = "subject_id")
    @JsonBackReference
    private Subject subject;

    public RegisterSubject(Long studentId, Long subjectId, LocalDate date) {

        this.date=date;
    }

}
