package student.manager.CRUD.domains.classes.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import student.manager.CRUD.application.base.BaseEntity;
import student.manager.CRUD.domains.subject.model.entity.Subject;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "classes")
@Data
@NoArgsConstructor
public class Classes extends BaseEntity {

    @Column(name = "class_name")
    private String name;

    @OneToOne(mappedBy = "classes", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Subject subject;

    public Classes(String name) {
        this.name=name;
    }

}
