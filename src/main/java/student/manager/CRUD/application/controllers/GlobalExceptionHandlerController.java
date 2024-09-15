package student.manager.CRUD.application.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import student.manager.CRUD.application.exceptions.*;

@ControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(StudentException.class)
    @ResponseBody
    public String handlerStudentException(StudentException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(TeacherException.class)
    @ResponseBody
    public String handlerTeacherException(TeacherException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(SubjectException.class)
    @ResponseBody
    public String handlerSubjectException(SubjectException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ScoreException.class)
    @ResponseBody
    public String handlerScoreException(ScoreException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ClassException.class)
    @ResponseBody
    public String handlerClassException(ClassException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(RegisterSubjectException.class)
    @ResponseBody
    public String handlerRegisterSubjectException(RegisterSubjectException ex) {
        return ex.getMessage();
    }
}
