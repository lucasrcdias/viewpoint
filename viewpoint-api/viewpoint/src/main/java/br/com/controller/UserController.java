package br.com.controller;

import br.com.HeaderParam;
import br.com.model.entity.User;
import br.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User create(@Validated @RequestBody UserDTO userDTO) {
        return getUserService().create(userDTO.getEmail(), userDTO.getPassword(), userDTO.getName());
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public User update(@RequestBody UserDTO userDTO, @RequestHeader(name = HeaderParam.AUTH_TOKEN) String token) {
        return getUserService().update(userDTO.getEmail(), userDTO.getPassword(), userDTO.getName(), token);
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
        return getUserService().findByEmail(email);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
