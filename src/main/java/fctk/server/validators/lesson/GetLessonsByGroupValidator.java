package fctk.server.validators.lesson;

import fctk.server.requests.lesson.GetLessonsByGroupRequest;
import fctk.server.validators.Validator;
import fctk.server.validators.primitive.LongValidator;

import java.util.ArrayList;
import java.util.List;

public class GetLessonsByGroupValidator implements Validator<GetLessonsByGroupRequest> {
    private final LongValidator longValidator;

    public GetLessonsByGroupValidator(LongValidator longValidator) {
        this.longValidator = longValidator;
    }

    @Override
    public List<String> validate(GetLessonsByGroupRequest getLessonsByGroupRequest) {
        var groupId = getLessonsByGroupRequest.groupId();
        return new ArrayList<>(longValidator.validate(groupId, "GroupId"));
    }
}
