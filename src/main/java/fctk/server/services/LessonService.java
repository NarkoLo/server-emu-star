package fctk.server.services;

import fctk.server.entities.LessonDTO;
import fctk.server.exceptions.RepositoryException;
import fctk.server.exceptions.ServiceException;
import fctk.server.repositories.interfaces.ILessonRepository;
import fctk.server.services.interfaces.ILessonService;

import java.time.LocalDate;
import java.util.List;

public class LessonService implements ILessonService {
    private final ILessonRepository lessonRepository;

    public LessonService(ILessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public long add(LessonDTO lesson) throws ServiceException {
        try {
            var result = lessonRepository.add(lesson);
            if (result == null) {
                throw new ServiceException("Teacher od Group not found");
            }
            return result;
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(LessonDTO lesson) throws ServiceException {
        try {
            if (lessonRepository.update(lesson) == null) {
                throw new ServiceException("Lesson with id =" + lesson.id() + " not found");
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            lessonRepository.deleteById(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public LessonDTO get(long id) throws ServiceException {
        try {
            var result = lessonRepository.getById(id);
            if (result == null) {
                throw new ServiceException("Lesson with id = " + id + " not found");
            }
            return result;
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<LessonDTO> getLessonsByGroup(LocalDate start, LocalDate end, long id) throws ServiceException {
        try {
            var result = lessonRepository.getLessonsByGroup(start, end, id);
            if (result == null) {
                throw new ServiceException("Group with id = " + id + " not found");
            }
            return result;
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<LessonDTO> getLessonsByTeacher(LocalDate start, LocalDate end, long id) throws ServiceException {
        try {
            var result = lessonRepository.getLessonsByTeacher(start, end, id);
            if (result == null) {
                throw new ServiceException("Teacher with id = " + id + " not found");
            }
            return result;
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
