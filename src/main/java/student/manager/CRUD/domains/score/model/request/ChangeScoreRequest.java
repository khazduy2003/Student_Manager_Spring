package student.manager.CRUD.domains.score.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChangeScoreRequest {
    private Long studentId;
    private Long subjectId;
    private BigDecimal score;

    private Long newStudentId;
    private Long newSubjectId;
    private BigDecimal newScore;

}
