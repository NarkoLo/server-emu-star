package fctk.server.entities;

import java.time.LocalDate;

public record LessonDTO(Long id, LocalDate date, int numberInSchedule, TeacherDTO teacher, GroupDTO group) {
}
