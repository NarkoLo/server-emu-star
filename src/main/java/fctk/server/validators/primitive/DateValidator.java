package fctk.server.validators.primitive;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DateValidator {
    public List<String> validateRegEx(String dateString, String pattern) {
        var result = new ArrayList<String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateString);
        } catch (ParseException e) {
            result.add(e.getMessage());
            return result;
        }
        return result;
    }

    public List<String> validateRegEx(String dateString) {
        var result = new ArrayList<String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateString);
        } catch (ParseException e) {
            result.add(e.getMessage());
            return result;
        }
        return result;
    }
}
