package fctk.server.validators.primitive;

import java.util.ArrayList;
import java.util.List;

public class StringValidator {

    public List<String> validate(String string, String fieldName) {
        var result = new ArrayList<String>();
        if (!notNull(string)) {
            result.add(fieldName + " is null!");
        } else {
            if (!notEmpty(string)) {
                result.add(fieldName + " is empty!");
            } else {
                if (!lessThan(string, 255)) {
                    result.add(fieldName + " is longer than!");
                }
            }
        }
        return result;
    }

    public boolean notNull(String aString) {
        return aString != null;
    }

    public boolean notEmpty(String aString) {
        return !"".equals(aString);
    }

    public boolean lessThan(String aString, int maxLength) {
        return aString.length() < maxLength;
    }
}
