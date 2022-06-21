package model;

import lombok.Builder;
import lombok.ToString;

@Builder(toBuilder = true)
@ToString
public class Student {
    private Integer id;
    private String name;
    private String surname;
    private String patronymic;
    private String birthDate;
    private String group;
}
