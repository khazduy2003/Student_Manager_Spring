package student.manager.CRUD.domains.teacher.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import student.manager.CRUD.application.base.BaseEntity;
import student.manager.CRUD.domains.subject.model.entity.Subject;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "teacher")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Teacher extends BaseEntity {

    @Column(name="name")
    @NonNull
    private String name;

    @Column(name="dob")
    @NonNull
    private LocalDate dob;

    @Column(name="department")
    @NonNull
    private String department;
    
    @Column(name = "address")
    @NonNull
    private String address;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Subject> subjectList;

}