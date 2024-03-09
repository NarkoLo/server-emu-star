package fctk.server.requests.lesson;

import java.time.LocalDate;

public record GetLessonsByTeacherRequest(LocalDate start, LocalDate end, long teacherId) {
}
