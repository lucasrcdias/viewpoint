package br.com.controller;

import br.com.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/action", produces = MediaType.APPLICATION_JSON_VALUE)

public class ActionController {

    @Autowired
    private ActionService actionService;

    public ActionService getActionService() {
        return actionService;
    }

    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }
}
