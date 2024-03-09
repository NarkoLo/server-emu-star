package fctk.server.requests.lesson;

import java.time.LocalDate;

public record GetLessonsByGroupRequest(LocalDate start, LocalDate end, long groupId) {
}
