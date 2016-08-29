package br.fatec.viewpoint;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.fatec.model.Action;
import br.fatec.model.User;
import br.fatec.service.EventActionService;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		EventActionService service = (EventActionService) context.getBean("eventActionService");

		User user = service.createUser("jequiti@gmail.com", "123456");

		service.createAction("click campo de pesquisa de produtos", "", "pesquisa na home", user);
		service.createAction("click no botao de pesquisar", "", "pesquisa na home", user);
		service.createAction("visualizacao do produto", "", "pesquisa na home", user);
		service.createAction("visualizacao do produto", "", "visualizacao do produto", user);
		service.createAction("mudanca de cor do produto", "", "visualizacao do produto", user);
		service.createAction("clicar em comprar", "", "visualizacao do produto", user);
		service.createAction("ver resumo de compra", "", "compra", user);
		service.createAction("Faz a compra", "", "compra", user);

		List<Action> actionsOne = service.findOneByGroup("pesquisa na home");

		System.out.println("pesquisa na home");
		System.out.println("Quantidade de ações com o grupo: " + actionsOne.size());
		for (Action action : actionsOne) {
			System.out.println("Name: " + action.getName());
			System.out.println("Group: " + action.getGroup());
			System.out.println("Date: " + action.getDate());
			User company = action.getUser();
			System.out.println("Email da empresa: " + company.getEmail());
			System.out.println("\n\n");
		}

		List<Action> actionsSecond = service.findOneByGroup("visualizacao do produto");
		System.out.println("Listando grupo : visualizacao do produto");
		System.out.println("Quantidade de ações com o grupo: " + actionsSecond.size());

		for (Action action : actionsSecond) {
			System.out.println("Nome: " + action.getName());
			System.out.println("Grupo: " + action.getGroup());
			System.out.println("Data: " + action.getDate());
			User company = action.getUser();
			System.out.println("Email da empresa: " + company.getEmail());
			System.out.println("\n\n");

		}

		List<Action> actionsThird = service.findOneByGroup("compra");
		System.out.println("Listando grupo : compra");
		System.out.println("Quantidade de ações com o grupo: " + actionsThird.size());

		for (Action action : actionsThird) {
			System.out.println("Nome: " + action.getName());
			System.out.println("Grupo: " + action.getGroup());
			System.out.println("Data: " + action.getDate());
			User company = action.getUser();
			System.out.println("Email da empresa: " + company.getEmail());
			System.out.println("\n\n");
		}
	}
}
