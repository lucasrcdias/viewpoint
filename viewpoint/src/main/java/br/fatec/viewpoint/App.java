package br.fatec.viewpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.fatec.repository.ActionRepository;
import br.fatec.repository.UserRepository;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		// Recupera os repositorios
		UserRepository autorizacaoRepo = (UserRepository) context.getBean("userRepository");
		ActionRepository usuarioRepo = (ActionRepository) context.getBean("actionRepository");

	}
}
