package fctk.server.repositories;

import fctk.server.repositories.dbentities.*;

import java.util.Map;

public record DataBase(
        Map<Long, GroupDB> groupsTable,
        Map<Long, LessonDB> lessonsTable,
        Map<Long, LessonVisitDB> lessonVisitsTable,
        Map<Long, StudentDB> studentsTable,
        Map<Long, SubjectDB> subjectsTable,
        Map<Long, TeacherDB> teachersTable
) {
}
