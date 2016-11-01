package br.com;

import br.com.exceptions.MissingHeaderParamException;
import br.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.Objects;

public class SecurityInterceptor extends HandlerInterceptorAdapter {


    private static final String CREATE_USER = "POST /api/user/create";
    private static final String CREATE_EVENT = "POST /api/action/create";
    private static final String LOGIN_USER = "POST /api/user/login";
    private static final String[] SKIP_SECURITY = {CREATE_USER, CREATE_EVENT, LOGIN_USER};
    private static final String EMPTY_HEADER_MESSAGE = "O parâmetro {0} não deveria ser nulo";

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (shouldSkipVerification(request, SKIP_SECURITY))
            return true;

        String authToken = request.getHeader(HeaderParam.AUTH_TOKEN);
        validateEmptyHeaders(authToken);

        return userService.authenticate(authToken);
    }

    void validateEmptyHeaders(String authorization) {
        if (!validParam(authorization)) {
            String errorMessage = MessageFormat.format(EMPTY_HEADER_MESSAGE, HeaderParam.AUTH_TOKEN);
            throw new MissingHeaderParamException(HeaderParam.AUTH_TOKEN, errorMessage);
        }
    }

    private boolean validParam(Object param) {
        return Objects.nonNull(param) && !param.toString().isEmpty();
    }

    public static Boolean shouldSkipVerification(HttpServletRequest request, String... skipRequests) {
        String url = request.getMethod() + " " + request.getRequestURI();
        for (String skip : skipRequests) {
            if (url.equalsIgnoreCase(skip)) {
                return true;
            }
        }
        return false;
    }

}
