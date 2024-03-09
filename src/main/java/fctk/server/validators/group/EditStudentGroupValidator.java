package fctk.server.validators.group;

import fctk.server.requests.group.EditStudentGroupRequest;
import fctk.server.validators.Validator;
import fctk.server.validators.primitive.LongValidator;
import fctk.server.validators.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class EditStudentGroupValidator implements Validator<EditStudentGroupRequest> {

    private LongValidator longValidator;
    private StringValidator stringValidator;

    public EditStudentGroupValidator(LongValidator longValidator, StringValidator stringValidator) {
        this.longValidator = longValidator;
        this.stringValidator = stringValidator;
    }

    @Override
    public List<String> validate(EditStudentGroupRequest editStudentGroupRequest) {
        var result = new ArrayList<String>();
        var id = editStudentGroupRequest.id();
        var name = editStudentGroupRequest.name();
        if (!longValidator.notNull(id)) {
            result.add("Id is null!");
        }
        if (!longValidator.moreThanZero(id)) {
            result.add("Id is less than 0!");
        }
        if (!stringValidator.notNull(name)) {
            result.add("Name is null!");
        }
        if (!stringValidator.notEmpty(name)) {
            result.add("Name is empty!");
        }
        if (!stringValidator.lessThan(name, 255)) {
            result.add("Name is longer than!");
        }
        return result;
    }
}
