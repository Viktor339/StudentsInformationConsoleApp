package action;

import dto.ActionDto;
import lombok.RequiredArgsConstructor;
import service.StudentService;

@RequiredArgsConstructor
public class GetStudentAction implements Action {

    private final StudentService studentService;

    @Override
    public boolean isValid(String num) {
        return num.equals("3");
    }

    @Override
    public void doAction(ActionDto actionDto) {
        System.out.println(studentService.getAllStudents().toString());
    }

    @Override
    public ActionDto createActionDto() {
        return null;
    }
}
