package fctk.server.repositories.interfaces;

import fctk.server.entities.TeacherDTO;
import fctk.server.exceptions.RepositoryException;

public interface ITeacherRepository {
    long add(TeacherDTO teacher) throws RepositoryException;

    TeacherDTO getById(long id) throws RepositoryException;
}
