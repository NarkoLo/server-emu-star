package fctk.server.services.interfaces;

import fctk.server.entities.StudentDTO;
import fctk.server.exceptions.ServiceException;

import java.util.List;

public interface IStudentService {
    long addStudent(StudentDTO student) throws ServiceException;

    void updateStudent(StudentDTO student) throws ServiceException;

    void deleteStudent(long id) throws ServiceException;

    StudentDTO getStudent(long id) throws ServiceException;

    List<StudentDTO> getStudentsByGroupId(long groupId) throws ServiceException;
}
