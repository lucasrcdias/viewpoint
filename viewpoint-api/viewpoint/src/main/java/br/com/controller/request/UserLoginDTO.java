package br.com.controller.request;

import javax.validation.Valid;

public class UserLoginDTO {
    @Valid
    private UserLoginData user;

    public UserLoginData getUser() {
        return user;
    }

    public void setUser(UserLoginData user) {
        this.user = user;
    }
}
