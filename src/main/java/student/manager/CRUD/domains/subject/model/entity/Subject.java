package student.manager.CRUD.domains.subject.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class Subject extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name="credits")
    private Integer credits;


    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<RegisterSubject> registerSubjectList;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Score> scoreList;

    @OneToOne
    @JoinColumn(name = "class_id", nullable = false, referencedColumnName = "class_id")
    @JsonBackReference
    private Classes classes;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false, referencedColumnName = "teacher_id")
    @JsonBackReference
    private Teacher teacher;

    public Subject (String name, Integer credits, Long teacherId, Long classId) {
        this.name=name;
        this.credits=credits;
    }

}
