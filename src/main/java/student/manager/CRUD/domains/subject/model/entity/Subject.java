package student.manager.CRUD.domains.subject.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import student.manager.CRUD.application.base.BaseEntity;
import student.manager.CRUD.domains.classes.model.entity.Classes;
import student.manager.CRUD.domains.registersubject.model.entity.RegisterSubject;
import student.manager.CRUD.domains.score.model.entity.Score;
import student.manager.CRUD.domains.teacher.model.entity.Teacher;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "subject")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Subject extends BaseEntity {

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name="credits")
    @NonNull
    private Integer credits;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<RegisterSubject> registerSubjectList;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Score> scoreList;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    @NonNull
    private Classes classes;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    @NonNull
    private Teacher teacher;

}
