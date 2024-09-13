package student.manager.CRUD.domains.teacher.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import student.manager.CRUD.application.base.BaseEntity;
import student.manager.CRUD.domains.subject.model.entity.Subject;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "teacher")
@Data
@NoArgsConstructor
public class Teacher extends BaseEntity {

    @Column(name="name")
    private String name;

    @Column(name="dob")
    private Date dob;

    @Column(name="department")
    private String department;
    
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Subject> subjectList;

    public Teacher(String name, Date dob, String department, String address) {
        this.name=name;
        this.dob=dob;
        this.department=department;
        this.address=address;
    }
}