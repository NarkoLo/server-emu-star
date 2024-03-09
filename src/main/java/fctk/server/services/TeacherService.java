package fctk.server.services;

import fctk.server.entities.TeacherDTO;
import fctk.server.exceptions.RepositoryException;
import fctk.server.exceptions.ServiceException;
import fctk.server.repositories.interfaces.ITeacherRepository;
import fctk.server.services.interfaces.ITeacherService;

public class TeacherService implements ITeacherService {
    private final ITeacherRepository teacherRepository;

    public TeacherService(ITeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @Override
    public long add(TeacherDTO teacher) throws ServiceException {
        try {
            return teacherRepository.add(teacher);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public TeacherDTO get(long id) throws ServiceException {
        try {
            var result = teacherRepository.getById(id);
            if (result == null) {
                throw new ServiceException("Teacher with id = " + id + " not found!");
            }
            return result;
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
