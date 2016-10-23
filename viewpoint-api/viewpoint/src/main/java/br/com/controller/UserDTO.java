package br.com.controller;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserDTO {
    @NotEmpty(message = "O campo de email não deveria ser vazio")
    @Email(message = "o campo de email não está em um formato correto")
    @Length(max = 254, message = "O campo de email deve ter o tamanho até 254 caracteres")
    private String email;

    @Length(min = 6, message = "O campo de senha deve ter o tamanho de no minimo 6 caracteres")
    @NotEmpty(message = "o campo de senha não deveria ser vazio")
    private String password;

    @Length(max = 254, message = "O campo de naome deve ter o tamanho até 60 caracteres")
    @NotEmpty(message = "O campo nome não deveria ser vazio")
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
