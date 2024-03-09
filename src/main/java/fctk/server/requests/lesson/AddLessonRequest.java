package fctk.server.requests.lesson;

import java.time.LocalDate;

public record AddLessonRequest(LocalDate date, int numberInSchedule, long teacherId, long groupId) {
}
