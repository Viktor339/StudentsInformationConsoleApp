package action;

import dto.ActionDto;

public interface Action {
    boolean isValid(String num);

    void doAction(ActionDto actionDto);

    ActionDto createActionDto();

}
