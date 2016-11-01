package br.com.service;

import br.com.controller.request.ActionDataDTO;
import br.com.controller.response.UserGroup;
import br.com.exceptions.UserNotFoundException;
import br.com.model.ActionRepository;
import br.com.model.entity.Action;
import br.com.model.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private UserService userService;
    ObjectMapper objectMapper = new ObjectMapper();

    public Action create(ActionDataDTO dto) throws JsonProcessingException {
        User user = userService.findOneByKey(dto.getKey());
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("key", "Usuário não encontrado pela chave");
        }
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
        User user = userService.tokenValidation(token);
        List<Action> actions = actionRepository.findAllByGroupAndUser(user.getId(), group);
        actionRepository.delete(actions);
    }

    public void deleteAllByName(String name, String token) {
        userService.tokenValidation(token);
        List<Action> actions = actionRepository.findAllByName(name);
        actionRepository.delete(actions);
    }

    public UserGroup findAllGroupsByUser(String token) {
        User user = userService.tokenValidation(token);
        List<Action> allGroupByUser = actionRepository.findAllGroupByUser(user.getId());
        List<String> actionsName = allGroupByUser.stream().map(Action::getGroup).collect(Collectors.toList());
        return new UserGroup(actionsName);
    }

    public List<Action> findAllActionsByUser(String token, String group) {
        User user = userService.tokenValidation(token);
        return actionRepository.findAllByGroupAndUser(user.getId(), group);
    }
}
