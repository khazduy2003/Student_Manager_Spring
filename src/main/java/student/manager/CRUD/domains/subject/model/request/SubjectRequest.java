package student.manager.CRUD.domains.subject.model.request;

import lombok.Data;

@Data
public class SubjectRequest {
    private String name;
    private Integer credits;
    private Long teacherId;
    private Long classId;
}
