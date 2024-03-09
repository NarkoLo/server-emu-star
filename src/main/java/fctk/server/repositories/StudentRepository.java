package fctk.server.repositories;

import fctk.server.entities.GroupDTO;
import fctk.server.entities.StudentDTO;
import fctk.server.exceptions.RepositoryException;
import fctk.server.repositories.dbentities.GroupDB;
import fctk.server.repositories.dbentities.StudentDB;
import fctk.server.repositories.interfaces.IStudentRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentRepository implements IStudentRepository {

    private final Map<Long, StudentDB> studentMap;
    private final Map<Long, GroupDB> groupMap;

    public StudentRepository(DataBase dataBase) {
        studentMap = dataBase.studentsTable();
        groupMap = dataBase.groupsTable();
    }


    @Override
    public long add(StudentDTO student) throws RepositoryException {
        long currentId = !studentMap.isEmpty() ? Collections.max(studentMap.keySet()) + 1 : 1;
        studentMap.put(currentId, new StudentDB(student.surname(), student.name(), student.patronymic(), student.status(), student.group().id()));
        return currentId;
    }

    @Override
    public Long update(StudentDTO student) throws RepositoryException {
        Long studentId = student.id();
        if (studentMap.containsKey(studentId)) {
            studentMap.put(studentId, new StudentDB(student.surname(), student.name(), student.patronymic(), student.status(), student.group().id()));
            return studentId;
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(long id) throws RepositoryException {
        studentMap.remove(id);
    }

    @Override
    public StudentDTO getById(long id) throws RepositoryException {
        var student = studentMap.getOrDefault(id, null);
        if (student == null) {
            return null;
        } else {
            var group = groupMap.get(student.groupId());
            return new StudentDTO(id, student.surname(), student.name(), student.patronymic(), student.status(), new GroupDTO(student.groupId(), group.name()));
        }
    }

    @Override
    public List<StudentDTO> getStudentsByGroupId(long groupId) throws RepositoryException {
        if (groupMap.containsKey(groupId)) {
            var groupDb = groupMap.get(groupId);
            var group = new GroupDTO(groupId, groupDb.name());
            return studentMap.entrySet().stream().filter(x -> x.getValue().groupId() == groupId).map(
                    entry -> {
                        var student = entry.getValue();
                        return new StudentDTO(entry.getKey(), student.surname(), student.name(), student.patronymic(), student.status(), group);
                    }
            ).collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
