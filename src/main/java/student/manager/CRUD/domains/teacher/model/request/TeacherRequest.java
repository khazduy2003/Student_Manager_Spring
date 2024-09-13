package student.manager.CRUD.domains.teacher.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class TeacherRequest {
    private String name;
    private Date dob;
    private String department;
    private String address;
}
