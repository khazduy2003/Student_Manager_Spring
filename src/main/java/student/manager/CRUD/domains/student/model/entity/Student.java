package student.manager.CRUD.domains.student.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import student.manager.CRUD.application.base.BaseEntity;
import student.manager.CRUD.domains.registersubject.model.entity.RegisterSubject;
import student.manager.CRUD.domains.score.model.entity.Score;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "student")
@Data
public class Student extends BaseEntity {

    @Column
    private String name;

    @Column
    private Date dob;

    @Column
    private String address;

    // OneToMany relationship with Score
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Score> scoreList;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<RegisterSubject> registerSubjectList;

    public Student(String name, Date dob, String address) {
        this.name=name;
        this.dob=dob;
        this.address=address;
    }
}
