package student.manager.CRUD.domains.score.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ScoreRequest {
    private Long studentId;
    private Long subjectId;
    private BigDecimal score;
}
