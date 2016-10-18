package br.com.controller;

import org.hibernate.validator.constraints.NotEmpty;

public class UserDTO {
    @NotEmpty(message = "the field email should not be empty")
    private String email;

    @NotEmpty(message = "the field password should not be empty")
    private String password;

    @NotEmpty(message = "the field name should not be empty")
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
