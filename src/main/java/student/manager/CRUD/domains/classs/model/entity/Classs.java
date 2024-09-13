package student.manager.CRUD.domains.classs.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import student.manager.CRUD.application.base.BaseEntity;
import student.manager.CRUD.domains.subject.model.entity.Subject;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "class")
@Data
public class Classs extends BaseEntity {

    @Column(name = "class_name")
    private String name;

    @OneToOne(mappedBy = "classs", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Subject subject;

    public Classs(String name) {
        this.name=name;
    }

}
