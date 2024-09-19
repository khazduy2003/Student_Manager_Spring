package student.manager.CRUD.domains.teacher.model.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TeacherRequest {
    private String name;
    private LocalDate dob;
    private String department;
    private String address;
}
