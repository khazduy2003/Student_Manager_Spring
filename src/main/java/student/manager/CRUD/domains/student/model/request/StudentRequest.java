package student.manager.CRUD.domains.student.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class StudentRequest {
    private String name;
    private LocalDate dob;
    private String address;
}
