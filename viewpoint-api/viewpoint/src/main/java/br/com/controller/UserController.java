package br.com.controller;

import br.com.HeaderParam;
import br.com.controller.request.UserDTO;
import br.com.controller.request.UserDataDTO;
import br.com.controller.request.UserLoginDTO;
import br.com.controller.request.UserLoginData;
import br.com.model.entity.User;
import br.com.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User login(@Validated @RequestBody UserLoginDTO userLoginDTO) throws JsonProcessingException {
        UserLoginData user = userLoginDTO.getUser();
        return getUserService().login(user.getEmail(), user.getPassword());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User create(@Validated @RequestBody UserDTO userDTO) throws JsonProcessingException {
        UserDataDTO user = userDTO.getUser();
        return getUserService().create(user.getEmail(), user.getPassword(), user.getName());
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public User update(@RequestBody UserDTO userDTO, @RequestHeader(name = HeaderParam.AUTH_TOKEN) String token) throws JsonProcessingException {
        UserDataDTO user = userDTO.getUser();
        return getUserService().update(user.getEmail(), user.getPassword(), user.getName(), token);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestHeader(name = HeaderParam.AUTH_TOKEN) String token) {
        getUserService().delete(token);
    }

    @RequestMapping(value = "/findByKey", method = RequestMethod.GET)
    public User findByKey(@RequestParam(required = true) String key) {
        return getUserService().findByKey(key);
    }

    @RequestMapping(value = "/findByEmail", method = RequestMethod.GET)
    public User findByEmail(@RequestParam(required = true) String email) {
        return getUserService().findOneByEmail(email);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
