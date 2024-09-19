package student.manager.CRUD.domains.registersubject.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import student.manager.CRUD.application.base.BaseEntity;
import student.manager.CRUD.domains.student.model.entity.Student;
import student.manager.CRUD.domains.subject.model.entity.Subject;
import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "registersubject")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class RegisterSubject extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    @NonNull
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    @NonNull
    private Subject subject;

    @Column(name = "register_date")
    @NonNull
    private LocalDate date;

}
