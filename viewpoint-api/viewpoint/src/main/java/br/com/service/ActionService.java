package br.com.service;

import br.com.controller.ActionDTO;
import br.com.exceptions.UserNotFoundException;
import br.com.model.ActionRepository;
import br.com.model.UserRepository;
import br.com.model.entity.Action;
import br.com.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private UserRepository userRepository;

    public Action create(ActionDTO dto, String token) {

        String jwt = token.substring("Bearer ".length());
        User user = userRepository.findOneByKey(jwt);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("Authorization", "Usuário não encontrado pela chave");
        }
        Action action = new Action();
        action.setUser(user);
        action.setName(dto.getName());
        action.setGroup(dto.getGroup());
        action.setIpAddress(dto.getIp());
        if (Objects.nonNull(dto.getParameters())) {
            action.setParameters(dto.getParameters().toString());
        }
        return actionRepository.save(action);
    }

    public void deleteAllByGroup(String group, String token) {
        String jwt = token.substring("Bearer ".length());
        User user = userRepository.findOneByKey(jwt);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("Authorization", "Usuário não encontrado pela chave");
        }
        List<Action> actions = actionRepository.findAllByGroup(group);
        actionRepository.delete(actions);
    }

    public void deleteAllByName(String name, String token) {
        String jwt = token.substring("Bearer ".length());
        User user = userRepository.findOneByKey(jwt);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("Authorization", "Usuário não encontrado pela chave");
        }
        List<Action> actions = actionRepository.findAllByName(name);
        actionRepository.delete(actions);
    }
}
