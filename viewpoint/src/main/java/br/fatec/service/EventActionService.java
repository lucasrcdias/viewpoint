package br.fatec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.model.Action;
import br.fatec.model.User;
import br.fatec.repository.ActionRepository;
import br.fatec.repository.UserRepository;

@Service
public class EventActionService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ActionRepository actionRepository;

	public User createUser(String email, String key) {
		User user = new User();
		user.setEmail(email);
		user.setKey(key);
		return userRepository.save(user);
	}

	public Action createAction(String name, String features, User user) {
		Action action = new Action();
		action.setName(name);
		action.setFeatures(features);
		action.setUser(user);
		return actionRepository.save(action);
	}
}
