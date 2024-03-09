package fctk.server.services;

import fctk.server.entities.SubjectDTO;
import fctk.server.exceptions.RepositoryException;
import fctk.server.exceptions.ServiceException;
import fctk.server.repositories.interfaces.ISubjectRepository;
import fctk.server.services.interfaces.ISubjectService;

import java.util.List;

public class SubjectService implements ISubjectService {

    private final ISubjectRepository subjectRepository;

    public SubjectService(ISubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public long add(SubjectDTO subject) throws ServiceException {
        try {
            return subjectRepository.add(subject);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(SubjectDTO subject) throws ServiceException {
        try {
            if (subjectRepository.update(subject) == null) {
                throw new ServiceException("Subject with id = " + subject.id() + " not found!");
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            subjectRepository.deleteById(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public SubjectDTO get(long id) throws ServiceException {
        try {
            var result = subjectRepository.getById(id);
            if (result == null) {
                throw new ServiceException("Subject with id = " + id + " not found!");
            }
            return result;
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<SubjectDTO> getAll() throws ServiceException {
        try {
            var result = subjectRepository.getAll();
            if (result == null) {
                throw new ServiceException("Subjects not found!");
            }
            return result;
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
