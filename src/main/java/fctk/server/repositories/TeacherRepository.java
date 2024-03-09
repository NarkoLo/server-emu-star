package fctk.server.repositories;

import fctk.server.entities.TeacherDTO;
import fctk.server.exceptions.RepositoryException;
import fctk.server.repositories.dbentities.TeacherDB;
import fctk.server.repositories.interfaces.ITeacherRepository;

import java.util.Collections;
import java.util.Map;

public class TeacherRepository implements ITeacherRepository {
    private final Map<Long, TeacherDB> teacherMap;

    public TeacherRepository(DataBase dataBase) {
        this.teacherMap = dataBase.teachersTable();
    }

    @Override
    public long add(TeacherDTO teacher) throws RepositoryException {
        long currentId = !teacherMap.isEmpty() ? Collections.max(teacherMap.keySet()) + 1 : 1;
        teacherMap.put(currentId, new TeacherDB(teacher.surname(), teacher.name(), teacher.patronymic(), teacher.position()));
        return currentId;
    }

    @Override
    public TeacherDTO getById(long id) throws RepositoryException {
        var teacher = teacherMap.getOrDefault(id, null);
        if (teacher == null) {
            return null;
        } else {
            return new TeacherDTO(id, teacher.surname(), teacher.name(), teacher.patronymic(), teacher.position());
        }
    }
}
