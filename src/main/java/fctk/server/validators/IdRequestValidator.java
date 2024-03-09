package fctk.server.validators;

import fctk.server.requests.IdRequest;
import fctk.server.validators.primitive.LongValidator;

import java.util.ArrayList;
import java.util.List;

public class IdRequestValidator implements Validator<IdRequest> {

    private LongValidator longValidator;

    public IdRequestValidator(LongValidator longValidator) {
        this.longValidator = longValidator;
    }

    @Override
    public List<String> validate(IdRequest idRequest) {
        var result = new ArrayList<String>();
        var id = idRequest.id();
        if (!longValidator.notNull(id)) {
            result.add("Id is Null");
        }
        if (!longValidator.moreThanZero(id)) {
            result.add("Id is less than 0");
        }
        return result;
    }
}
