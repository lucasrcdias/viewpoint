package br.com.controller.request;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserDataDTO {

    @NotEmpty(message = "O preenchimento do e-mail é obrigatório")
    @Email(message = "O e-mail informado não é válido")
    @Length(max = 254, message = "O e-mail pode conter no máximo 254 caracteres")
    private String email;

    @Length(min = 6, message = "A senha deve conter no mínimo 6 caracteres")
    @NotEmpty(message = "O preenchimento da senha é obrigatório")
    private String password;

    @Length(max = 60, message = "O nome deve conter no máximo 60 caracteres")
    @NotEmpty(message = "O preenchimento do nome é obrigatório")
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
