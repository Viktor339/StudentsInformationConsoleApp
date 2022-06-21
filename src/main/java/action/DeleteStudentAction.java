package action;

import dto.ActionDto;
import dto.DeleteStudentDto;
import lombok.RequiredArgsConstructor;
import service.StudentService;

import java.io.Console;

@RequiredArgsConstructor
public class DeleteStudentAction implements Action {

    private final Console console;
    private final StudentService studentService;

    @Override
    public boolean isValid(String num) {
        return num.equals("2");
    }

    @Override
    public void doAction(ActionDto actionDto) {

        DeleteStudentDto deleteStudentDto = (DeleteStudentDto) actionDto;

        studentService.delete(deleteStudentDto);

        System.out.println("\nStudent deleted");

    }

    @Override
    public ActionDto createActionDto() {


        System.out.println("Enter student id: ");

        String studentId = console.readLine();


        return DeleteStudentDto.builder()
                .id(Long.parseLong(studentId))
                .build();

    }
}
