package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
@Setter
public class AddStudentDto implements ActionDto {

    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String birthDate;
    private String group;

}
