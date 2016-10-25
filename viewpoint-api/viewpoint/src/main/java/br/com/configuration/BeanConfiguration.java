package br.com.configuration;

import br.com.SecurityInterceptor;
import br.com.service.ActionService;
import br.com.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
@ComponentScan(value = {"br.com"})
public class BeanConfiguration extends WebMvcConfigurationSupport {

    private static final String USER_API = "/api/user*/**";
    private static final String ACTION_USER = "/api/action*/**";
    private static final String[] SECURE_ENDPOINTS = {USER_API, ACTION_USER};

    @Bean
    public UserService clientService() {
        return new UserService();
    }

    @Bean
    public ActionService actionService() {
        return new ActionService();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns(SECURE_ENDPOINTS);
    }

    @Override
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter adapter = super.requestMappingHandlerAdapter();
        adapter.setIgnoreDefaultModelOnRedirect(true);
        return adapter;
    }
}
