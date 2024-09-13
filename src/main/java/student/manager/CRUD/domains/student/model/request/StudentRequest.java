package student.manager.CRUD.domains.student.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
public class StudentRequest {
    private String name;
    private Date dob;
    private String address;

}
