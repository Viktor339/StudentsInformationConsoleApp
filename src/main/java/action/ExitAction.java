package action;

import dto.ActionDto;

import static java.lang.System.exit;

public class ExitAction implements Action {
    @Override
    public boolean isValid(String num) {
        return num.equals("4");
    }

    @Override
    public void doAction(ActionDto actionDto) {
        exit(0);
    }

    @Override
    public ActionDto createActionDto() {
        return null;
    }
}
