package fctk.server.validators.lesson;

import fctk.server.requests.lesson.EditLessonRequest;
import fctk.server.validators.Validator;
import fctk.server.validators.primitive.LongValidator;

import java.util.ArrayList;
import java.util.List;

public class EditLessonValidator implements Validator<EditLessonRequest> {
    private final LongValidator longValidator;

    public EditLessonValidator(LongValidator longValidator) {
        this.longValidator = longValidator;
    }


    @Override
    public List<String> validate(EditLessonRequest editLessonRequest) {
        var result = new ArrayList<String>();
        var id = editLessonRequest.id();
        var teacherId = editLessonRequest.teacherId();
        var groupId = editLessonRequest.groupId();
        result.addAll(longValidator.validate(id, "Id"));
        result.addAll(longValidator.validate(teacherId, "TeacherId"));
        result.addAll(longValidator.validate(groupId, "GroupId"));
        return result;
    }
}
