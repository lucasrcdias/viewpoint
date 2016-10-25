package br.com.service;

import br.com.controller.ActionDTO;
import br.com.model.ActionRepository;
import br.com.model.entity.Action;
import br.com.model.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private UserService userService;
    ObjectMapper objectMapper = new ObjectMapper();

    public Action create(ActionDTO dto, String token) throws JsonProcessingException {
        User user = userService.findUserByToken(token);
        Action action = new Action();
        action.setUser(user);
        action.setName(dto.getName());
        action.setGroup(dto.getGroup());
        action.setIpAddress(dto.getIp());
        if (Objects.nonNull(dto.getParameters())) {
            String json = objectMapper.writeValueAsString(dto.getParameters());
            action.setParameters(json);
        }
        return actionRepository.save(action);
    }

    public void deleteAllByGroup(String group, String token) {
        userService.findUserByToken(token);
        List<Action> actions = actionRepository.findAllByGroup(group);
        actionRepository.delete(actions);
    }

    public void deleteAllByName(String name, String token) {
        userService.findUserByToken(token);
        List<Action> actions = actionRepository.findAllByName(name);
        actionRepository.delete(actions);
    }
}
