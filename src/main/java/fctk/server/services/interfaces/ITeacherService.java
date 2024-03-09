package fctk.server.services.interfaces;

import fctk.server.entities.TeacherDTO;
import fctk.server.exceptions.ServiceException;

public interface ITeacherService {
    long add(TeacherDTO teacher) throws ServiceException;

    TeacherDTO get(long id) throws ServiceException;
}
