package service.validator;

import dto.AddStudentDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatronymicValidator implements Validator<AddStudentDto> {

    @Override
    public boolean isValid(AddStudentDto value) {

        String regex = "^[A-Za-z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value.getPatronymic());

        return !matcher.matches();
    }


    @Override
    public String getMessage() {
        return "Incorrect patronymic. Patronymic must contain only Latin characters";
    }
}
