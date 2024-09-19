package student.manager.CRUD.domains.student.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ChangeStudentRequest {

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private String address;
    private String newName;
    private LocalDate newDob;
    private String newAddress;
}
