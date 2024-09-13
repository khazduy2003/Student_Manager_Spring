package student.manager.CRUD.domains.teacher.service;

import student.manager.CRUD.domains.teacher.model.entity.Teacher;
import student.manager.CRUD.domains.teacher.model.request.ChangeTeacherRequest;
import student.manager.CRUD.domains.teacher.model.request.TeacherRequest;

import java.util.List;

public interface TeacherService {
    Teacher getTeacherById(Long id);
    List<Teacher> getAllOfTeacher();
    TeacherRequest addTeacher(TeacherRequest request);

    String deleteTeacherById(Long id);

    String changeInfo(Long id, ChangeTeacherRequest request);
}
