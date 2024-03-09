package fctk.server.repositories;

import fctk.server.entities.GroupDTO;
import fctk.server.entities.LessonDTO;
import fctk.server.entities.TeacherDTO;
import fctk.server.exceptions.RepositoryException;
import fctk.server.repositories.dbentities.GroupDB;
import fctk.server.repositories.dbentities.LessonDB;
import fctk.server.repositories.dbentities.TeacherDB;
import fctk.server.repositories.interfaces.ILessonRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LessonRepository implements ILessonRepository {

    private final Map<Long, LessonDB> lessonMap;
    private final Map<Long, TeacherDB> teacherMap;
    private final Map<Long, GroupDB> groupMap;

    public LessonRepository(DataBase dataBase) {
        this.lessonMap = dataBase.lessonsTable();
        this.teacherMap = dataBase.teachersTable();
        this.groupMap = dataBase.groupsTable();
    }

    @Override
    public Long add(LessonDTO lesson) throws RepositoryException {
        long currentId = !lessonMap.isEmpty() ? Collections.max(lessonMap.keySet()) + 1 : 1;
        var teacherId = lesson.teacher().id();
        var groupId = lesson.group().id();
        if (!teacherMap.containsKey(teacherId) || !groupMap.containsKey(groupId)) {
            return null;
        }
        lessonMap.put(currentId, new LessonDB(lesson.date(), lesson.numberInSchedule(), teacherId, groupId));
        return currentId;
    }

    @Override
    public Long update(LessonDTO lesson) throws RepositoryException {
        Long lessonId = lesson.id();
        if (lessonMap.containsKey(lessonId)) {
            var teacherId = lesson.teacher().id();
            var groupId = lesson.group().id();
            lessonMap.put(lessonId, new LessonDB(lesson.date(), lesson.numberInSchedule(), teacherId, groupId));
            return groupId;
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(long id) throws RepositoryException {
        lessonMap.remove(id);
    }

    @Override
    public LessonDTO getById(long id) throws RepositoryException {
        var lesson = lessonMap.getOrDefault(id, null);
        if (lesson == null) {
            return null;
        } else {
            var teacherId = lesson.teacherId();
            var groupId = lesson.groupId();
            var teacherDb = teacherMap.get(teacherId);
            var groupDb = groupMap.get(groupId);
            var teacher = new TeacherDTO(teacherId, teacherDb.surname(), teacherDb.name(), teacherDb.patronymic(), teacherDb.position());
            var group = new GroupDTO(groupId, groupDb.name());
            return new LessonDTO(id, lesson.date(), lesson.numberInSchedule(), teacher, group);
        }
    }

    @Override
    public List<LessonDTO> getLessonsByGroup(LocalDate start, LocalDate end, long id) throws RepositoryException {
        var result = new ArrayList<LessonDTO>();
        if (!groupMap.containsKey(id)) {
            return null;
        }
        return lessonMap
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().groupId() == id && start.isBefore(entry.getValue().date()) && end.isAfter(entry.getValue().date())
                )
                .map(entry -> {
                    var lessonId = entry.getKey();
                    var lessonDb = entry.getValue();
                    var date = lessonDb.date();
                    var teacherId = lessonDb.teacherId();
                    var teacherDb = teacherMap.get(teacherId);
                    var groupDb = groupMap.get(id);
                    var teacher = new TeacherDTO(teacherId, teacherDb.surname(), teacherDb.name(), teacherDb.patronymic(), teacherDb.position());
                    var group = new GroupDTO(id, groupDb.name());
                    return new LessonDTO(lessonId, date, lessonDb.numberInSchedule(), teacher, group);
                }).collect(Collectors.toList());
    }

    @Override
    public List<LessonDTO> getLessonsByTeacher(LocalDate start, LocalDate end, long id) throws RepositoryException {
        var result = new ArrayList<LessonDTO>();
        if (!teacherMap.containsKey(id)) {
            return null;
        }
        return lessonMap
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().groupId() == id && start.isBefore(entry.getValue().date()) && end.isAfter(entry.getValue().date())
                )
                .map(entry -> {
                    var lessonId = entry.getKey();
                    var lessonDb = entry.getValue();
                    var date = lessonDb.date();
                    var groupId = lessonDb.groupId();
                    var teacherDb = teacherMap.get(id);
                    var groupDb = groupMap.get(groupId);
                    var teacher = new TeacherDTO(id, teacherDb.surname(), teacherDb.name(), teacherDb.patronymic(), teacherDb.position());
                    var group = new GroupDTO(groupId, groupDb.name());
                    return new LessonDTO(lessonId, date, lessonDb.numberInSchedule(), teacher, group);
                }).collect(Collectors.toList());
    }
}
