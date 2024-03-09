package fctk.server.requests.lesson;

import java.time.LocalDate;

public record EditLessonRequest(long id, LocalDate date, int numberInSchedule, long teacherId, long groupId) {
}
