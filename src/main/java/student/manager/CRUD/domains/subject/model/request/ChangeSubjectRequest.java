package student.manager.CRUD.domains.subject.model.request;

import lombok.Data;

@Data
public class ChangeSubjectRequest {

    private String name;
    private Integer credits;
    private Long teacherId;
    private Long classId;

    private String newName;
    private Integer newCredits;
    private Long newTeacherId;
    private Long newClassId;
}
