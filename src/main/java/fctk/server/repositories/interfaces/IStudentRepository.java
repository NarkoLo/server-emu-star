package fctk.server.repositories.interfaces;

import fctk.server.entities.StudentDTO;
import fctk.server.exceptions.RepositoryException;

import java.util.List;

public interface IStudentRepository {
    long add(StudentDTO student) throws RepositoryException;

    Long update(StudentDTO student) throws RepositoryException;

    void deleteById(long id) throws RepositoryException;

    StudentDTO getById(long id) throws RepositoryException;

    List<StudentDTO> getStudentsByGroupId(long groupId) throws RepositoryException;
}
