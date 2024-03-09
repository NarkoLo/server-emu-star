package fctk.server.services.interfaces;

import fctk.server.entities.SubjectDTO;
import fctk.server.exceptions.ServiceException;

import java.util.List;

public interface ISubjectService {
    long add(SubjectDTO subject) throws ServiceException;

    void update(SubjectDTO subject) throws ServiceException;

    void delete(long id) throws ServiceException;

    SubjectDTO get(long id) throws ServiceException;

    List<SubjectDTO> getAll() throws ServiceException;
}
