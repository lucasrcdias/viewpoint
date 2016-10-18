package br.com.controller;

import br.com.model.entity.User;
import br.com.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public User create(@RequestParam(required = true) String email) {
        return getUserService().create(email);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public User update(@RequestParam(required = true) Long id, @RequestParam(required = true) String email)
            throws NotFoundException {
        return getUserService().update(id, email);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void update(@RequestParam(required = true) Long id) throws NotFoundException {
        getUserService().delete(id);
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
