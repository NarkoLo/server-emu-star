package fctk.server.validators.student;

import fctk.server.requests.student.EditStudentRequest;
import fctk.server.validators.Validator;
import fctk.server.validators.primitive.LongValidator;
import fctk.server.validators.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class EditStudentValidator implements Validator<EditStudentRequest> {
    private final StringValidator stringValidator;
    private final LongValidator longValidator;

    public EditStudentValidator(StringValidator stringValidator, LongValidator longValidator) {
        this.stringValidator = stringValidator;
        this.longValidator = longValidator;
    }

    @Override
    public List<String> validate(EditStudentRequest editStudentRequest) {
        var result = new ArrayList<String>();
        var surname = editStudentRequest.surname();
        var name = editStudentRequest.name();
        var patronymic = editStudentRequest.patronymic();
        var groupId = editStudentRequest.groupId();
        var status = editStudentRequest.status();
        result.addAll(stringValidator.validate(surname, "LastName"));
        result.addAll(stringValidator.validate(name, "FirstName"));
        result.addAll(stringValidator.validate(patronymic, "MiddleName"));
        result.addAll(longValidator.validate(groupId, "GroupId"));
        result.addAll(stringValidator.validate(status, "Status"));
        return result;
    }
}
