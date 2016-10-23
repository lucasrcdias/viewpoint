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

    //Header param that should be send by user
    private static final String AUTH_TOKEN = "AUTH_TOKEN";
    private static final String CREATE_USER = "POST /api/user/create";
    private static final String[] SKIP_SECURITY = {CREATE_USER};
    private static final String EMPTY_HEADER_MESSAGE = "Header params {0} should not be null";

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (shouldSkipVerification(request, SKIP_SECURITY))
            return true;

        String authToken = request.getHeader(AUTH_TOKEN);
        validateEmptyHeaders(authToken);

        return userService.authenticate(authToken);
    }

    void validateEmptyHeaders(String authorization) {
        if (!validParam(authorization)) {
            String format = MessageFormat.format(EMPTY_HEADER_MESSAGE, AUTH_TOKEN);
            throw new MissingHeaderParamException(format);
        }
    }

    private boolean validParam(Object param) {
        return Objects.nonNull(param) && !param.toString().isEmpty();
    }

    public static Boolean shouldSkipVerification(HttpServletRequest request, String... skipRequests) {
        String url = request.getMethod() + " " + request.getRequestURI();
        return url.equalsIgnoreCase(skipRequests[0]);
    }

}
