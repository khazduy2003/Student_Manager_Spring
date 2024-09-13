package student.manager.CRUD.domains.teacher.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class ChangeTeacherRequest {
    private String name;
    private Date dob;
    private String department;
    private String address;

    private String newName;
    private Date newDob;
    private String newDepartment;
    private String newAddress;
}