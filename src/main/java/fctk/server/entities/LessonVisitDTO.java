package fctk.server.entities;

import java.util.List;

public record LessonVisitDTO(Long id, LessonDTO lesson, List<StudentDTO> studentList) {
}
