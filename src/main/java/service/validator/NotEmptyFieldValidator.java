package service.validator;

import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public class NotEmptyFieldValidator<T> implements Validator<T> {

    private final Function<T, ?> getter;
    private final String message;

    @Override
    public boolean isValid(T value) {
        return getter.apply(value) == null || getter.apply(value).toString().isEmpty();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
