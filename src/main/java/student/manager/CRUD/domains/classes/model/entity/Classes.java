package student.manager.CRUD.domains.classes.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import student.manager.CRUD.application.base.BaseEntity;
import student.manager.CRUD.domains.subject.model.entity.Subject;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "classes")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Classes extends BaseEntity {

    @Column(name = "class_name")
    @NonNull
    private String name;

    @OneToMany(mappedBy = "classes", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Subject> subjectList;
}
