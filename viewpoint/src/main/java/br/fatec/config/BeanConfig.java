package br.fatec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.fatec.model.Action;
import br.fatec.model.User;
import br.fatec.repository.UserRepository;
import br.fatec.service.EventActionService;

@Configuration
@ComponentScan(value={"br.fatec"})
public class BeanConfig {

	@Bean
	public User user(){
		return new User();
	}
	@Bean
	public Action action(){
		return new Action();
	}
	@Bean
	public EventActionService eventActionService(){
		return new EventActionService();
	}
	
}
