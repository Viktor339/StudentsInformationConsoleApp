package service.validator;

public interface Validator<T> {
    boolean isValid(T value) ;
    String  getMessage();
}
