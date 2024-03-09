package fctk.server.validators.subject;

import fctk.server.requests.subject.EditSubjectRequest;
import fctk.server.validators.Validator;
import fctk.server.validators.primitive.LongValidator;
import fctk.server.validators.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class EditSubjectValidator implements Validator<EditSubjectRequest> {

    private final StringValidator stringValidator;
    private final LongValidator longValidator;

    public EditSubjectValidator(StringValidator stringValidator, LongValidator longValidator) {
        this.stringValidator = stringValidator;
        this.longValidator = longValidator;
    }

    @Override
    public List<String> validate(EditSubjectRequest editSubjectRequest) {
        var result = new ArrayList<String>();
        var id = editSubjectRequest.id();
        var name = editSubjectRequest.name();
        result.addAll(longValidator.validate(id, "SubjectId"));
        result.addAll(stringValidator.validate(name, "SubjectName"));
        return result;
    }
}
