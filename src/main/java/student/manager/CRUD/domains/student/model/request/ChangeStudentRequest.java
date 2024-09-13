package student.manager.CRUD.domains.student.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class ChangeStudentRequest {
    private String name;
    private Date dob;
    private String address;
    private String newName;
    private Date newDob;
    private String newAddress;
}
