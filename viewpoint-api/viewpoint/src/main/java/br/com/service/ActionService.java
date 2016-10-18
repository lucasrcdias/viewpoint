package br.com.service;

import br.com.model.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

	@Autowired
	private ActionRepository actionRepository;

	public ActionRepository getActionRepository() {
		return actionRepository;
	}

	public void setActionRepository(ActionRepository actionRepository) {
		this.actionRepository = actionRepository;
	}
}
