package fctk.server.repositories.dbentities;

import java.util.List;

public record LessonVisitDB(long lessonId, List<Long> studentIdList) {
}
