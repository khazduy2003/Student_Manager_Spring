package student.manager.CRUD.domains.score.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class Score extends BaseEntity {

    @Column(name = "score")
    private BigDecimal score;

    @Column(name = "date_assessed")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false, referencedColumnName = "student_id")
    @JsonBackReference
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false,referencedColumnName = "subject_id")
    @JsonBackReference
    private Subject subject;

    public Score(Long studentId, Long subjectId, BigDecimal score, LocalDate date) {
        this.score=score;
        this.date=date;
    }
}
