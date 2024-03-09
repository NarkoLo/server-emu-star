package fctk.server.repositories.dbentities;

import java.time.LocalDate;

public record LessonDB(LocalDate date, int numberInSchedule, long teacherId, long groupId) {
}
