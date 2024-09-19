package student.manager.CRUD.domains.teacher.model.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ChangeTeacherRequest {
    private String name;
    private LocalDate dob;
    private String department;
    private String address;

    private String newName;
    private LocalDate newDob;
    private String newDepartment;
    private String newAddress;
}