package student.manager.CRUD.domains.classes.model.request;

import lombok.Data;

@Data
public class ChangeClassRequest {
    private String name;
    private String newName;
}
