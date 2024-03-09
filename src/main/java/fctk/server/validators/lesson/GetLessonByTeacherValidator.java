package fctk.server.validators.lesson;

import fctk.server.requests.lesson.GetLessonsByTeacherRequest;
import fctk.server.validators.Validator;
import fctk.server.validators.primitive.LongValidator;

import java.util.ArrayList;
import java.util.List;

public class GetLessonByTeacherValidator implements Validator<GetLessonsByTeacherRequest> {
    private final LongValidator longValidator;

    public GetLessonByTeacherValidator(LongValidator longValidator) {
        this.longValidator = longValidator;
    }

    @Override
    public List<String> validate(GetLessonsByTeacherRequest getLessonsByTeacherRequest) {
        var teacherId = getLessonsByTeacherRequest.teacherId();
        return new ArrayList<>(longValidator.validate(teacherId, "TeacherId"));
    }
}
