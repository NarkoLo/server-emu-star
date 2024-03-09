package fctk.server.validators.primitive;

import java.util.ArrayList;
import java.util.List;

public class LongValidator {

    public List<String> validate(Long aLong, String fieldName) {
        var result = new ArrayList<String>();
        if (!notNull(aLong)) {
            result.add(fieldName + " is null!");
        } else {
            if (!moreThanZero(aLong)) {
                result.add(fieldName + " less than 0!");
            }
        }
        return result;
    }

    public boolean notNull(Long aLong) {
        return aLong != null;
    }

    public boolean moreThanZero(Long aLong) {
        return aLong.compareTo(0L) > 0;
    }
}
