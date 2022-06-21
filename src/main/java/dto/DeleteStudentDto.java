package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
@Setter
public class DeleteStudentDto implements ActionDto {
    private Long id;
}
