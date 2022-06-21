package service.validator;

import dto.AddStudentDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BirthDateValidator implements Validator<AddStudentDto>{
    @Override
    public boolean isValid(AddStudentDto value) {

        String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value.getBirthDate());

        return !matcher.matches();
    }


    @Override
    public String getMessage() {
        return "Incorrect date. Date format is YYYY-MM-DD";
    }
}
