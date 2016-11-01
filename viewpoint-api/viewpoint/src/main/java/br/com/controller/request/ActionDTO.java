package br.com.controller.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ActionDTO {
    @Valid
    @NotNull(message = "Os dados do evento não podem ser nulo")
    private ActionDataDTO action;

    public ActionDataDTO getAction() {
        return action;
    }

    public void setAction(ActionDataDTO action) {
        this.action = action;
    }
}
