package action;

import dto.ActionDto;
import dto.AddStudentDto;
import lombok.RequiredArgsConstructor;
import service.StudentService;

import java.io.Console;

@RequiredArgsConstructor
public class AddStudentAction implements Action {

    private final Console console;
    private final StudentService studentService;

    @Override
    public boolean isValid(String num) {
        return num.equals("1");
    }

    @Override
    public void doAction(ActionDto actionDto) {

        AddStudentDto addStudentActionDto = (AddStudentDto) actionDto;
        studentService.saveStudent(addStudentActionDto);

        System.out.println("\nStudent added successfully");
    }


    @Override
    public ActionDto createActionDto() {

        System.out.println("Enter name: ");
        String name = console.readLine();

        System.out.println("Enter surname: ");
        String surname = console.readLine();

        System.out.println("Enter patronymic: ");
        String patronymic = console.readLine();

        System.out.println("Enter birth date (YYYY-MM-DD format): ");
        String birthDate = console.readLine();

        System.out.println("Enter group: ");
        String group = console.readLine();

        return AddStudentDto.builder()
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .birthDate(birthDate)
                .group(group)
                .build();

    }
}
