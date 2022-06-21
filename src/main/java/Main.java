import action.Action;
import action.AddStudentAction;
import action.DeleteStudentAction;
import action.ExitAction;
import action.GetStudentAction;
import configuration.Config;
import dto.ActionDto;
import dto.AddStudentDto;
import dto.DeleteStudentDto;
import init.InitData;
import repository.StudentRepository;
import service.StudentService;
import service.exception.ActionNotFoundException;
import service.validator.BirthDateValidator;
import service.validator.NameValidator;
import service.validator.NotEmptyFieldValidator;
import service.validator.PatronymicValidator;
import service.validator.SurNameValidator;
import service.validator.ValidatorService;

import java.io.Console;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Console console = System.console();

        Config config = new Config();
        InitData initData = new InitData(config);
        initData.createTable();

        StudentRepository studentRepository = new StudentRepository(config);
        ValidatorService validatorService = new ValidatorService();

        StudentService studentService = new StudentService(studentRepository, validatorService,
                List.of(new NotEmptyFieldValidator<>(AddStudentDto::getName, "Name is null or empty"),
                        new NotEmptyFieldValidator<>(AddStudentDto::getSurname, "Surname is null or empty"),
                        new NotEmptyFieldValidator<>(AddStudentDto::getPatronymic, "Patronymic is null or empty"),
                        new NotEmptyFieldValidator<>(AddStudentDto::getBirthDate, "Birth date is null or empty"),
                        new NotEmptyFieldValidator<>(AddStudentDto::getGroup, "Group is null or empty"),
                        new NameValidator(),
                        new SurNameValidator(),
                        new PatronymicValidator(),
                        new BirthDateValidator()
                ),
                List.of(new NotEmptyFieldValidator<>(DeleteStudentDto::getId, "Id is null or empty"))
        );

        List<Action> actions = List.of(new AddStudentAction(console, studentService),
                new DeleteStudentAction(console, studentService),
                new GetStudentAction(studentService),
                new ExitAction()
        );


        while (true) {

            if (console != null) {
                System.out.println("\nSelect the desired action number\n" +
                        "1 - add a student\n" +
                        "2 - delete a student\n" +
                        "3 - get all students\n" +
                        "4 - exit");

                String actionNum = console.readLine();

                final Action action = actions.stream()
                        .filter(act -> act.isValid(actionNum))
                        .findFirst()
                        .orElseThrow(() -> new ActionNotFoundException("Action not found"));

                ActionDto actionDto = action.createActionDto();
                action.doAction(actionDto);

            } else {
                System.out.println("Console not found");
            }
        }
    }
}
