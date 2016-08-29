package br.fatec.service;

import java.util.Date;
import java.util.List;

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
		user.setCreated_at(new Date());
		return userRepository.save(user);
	}

	public User findOneUser(Long id) {
		return userRepository.findOne(id);
	}

	public List<Action> findOneByGroup(String group) {
		return actionRepository.findOneByGroup(group);
	}

	public Action createAction(String name, String features, String group, User user) {
		Action action = new Action();
		action.setName(name);
		action.setFeatures(features);
		action.setUser(user);
		action.setDate(new Date());
		action.setGroup(group);
		return actionRepository.save(action);
	}
}
