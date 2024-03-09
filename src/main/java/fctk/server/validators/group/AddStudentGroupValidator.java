package fctk.server.validators.group;

import fctk.server.requests.group.AddStudentGroupRequest;
import fctk.server.validators.Validator;
import fctk.server.validators.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class AddStudentGroupValidator implements Validator<AddStudentGroupRequest> {

    private final StringValidator stringValidator;

    public AddStudentGroupValidator(StringValidator stringValidator) {
        this.stringValidator = stringValidator;
    }

    @Override
    public List<String> validate(AddStudentGroupRequest addStudentGroupRequest) {
        var name = addStudentGroupRequest.name();
        return new ArrayList<String>(stringValidator.validate(name, "Name"));
    }
}
