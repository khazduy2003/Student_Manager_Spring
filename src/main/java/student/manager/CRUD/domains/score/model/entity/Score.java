package student.manager.CRUD.domains.score.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import student.manager.CRUD.application.base.BaseEntity;
import student.manager.CRUD.domains.student.model.entity.Student;
import student.manager.CRUD.domains.subject.model.entity.Subject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "score")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Score extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    @NonNull
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false,referencedColumnName = "id")
    @JsonBackReference
    @NonNull
    private Subject subject;

    @Column(name = "score")
    @NonNull
    private BigDecimal score;

    @Column(name = "date_assessed")
    @NonNull
    private LocalDate date;

}
