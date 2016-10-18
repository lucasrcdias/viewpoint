package br.com.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.service.ActionService;
import br.com.service.UserService;

@Configuration
@ComponentScan(value = {"br.com"})
public class BeanConfiguration {

    @Bean
    public UserService clientService() {
        return new UserService();
    }

    @Bean
    public ActionService actionService() {
        return new ActionService();
    }
}
