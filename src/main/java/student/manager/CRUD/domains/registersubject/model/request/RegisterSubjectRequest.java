package student.manager.CRUD.domains.registersubject.model.request;

import lombok.Data;

@Data
public class RegisterSubjectRequest {
    private Long studentId;
    private Long subjectId;
}
