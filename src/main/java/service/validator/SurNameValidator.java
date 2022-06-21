package service.validator;

import dto.AddStudentDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SurNameValidator implements Validator<AddStudentDto> {

    @Override
    public boolean isValid(AddStudentDto value) {

        String regex = "^[A-Za-z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value.getSurname());

        return !matcher.matches();
    }


    @Override
    public String getMessage() {
        return "Incorrect surname. Surname must contain only Latin characters";
    }
}
