package br.com.controller.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class UserDTO {
    @Valid
    @NotNull(message = "Os dados do usuário não podem ser nulos")
    private UserDataDTO user;

    public UserDataDTO getUser() {
        return user;
    }

    public void setUser(UserDataDTO user) {
        this.user = user;
    }
}
