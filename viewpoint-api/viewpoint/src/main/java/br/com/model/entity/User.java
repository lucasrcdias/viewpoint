package br.com.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    @JsonProperty
    private Long id;

    @Column(name = "email_user", unique = true)
    @Length(max = 254, message = "O e-mail pode conter no máximo 254 caracteres")
    @JsonProperty
    @NotEmpty(message = "O preenchimento do e-mail é obrigatório")
    @Email
    private String email;

    @Column(name = "password_user")
    @Length(min = 6, message = "A senha deve conter no mínimo 6 caracteres")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty(message = "O preenchimento da senha é obrigatório")
    private String password;

    @Column(name = "name_user")
    @JsonProperty
    @Length(max = 60, message = "O nome deve conter no máximo 60 caracteres")
    @NotEmpty(message = "O preenchimento do nome é obrigatório")
    private String name;

    @Column(name = "api_key", unique = true, nullable = false)
    @JsonProperty
    @Length(max = 150)
    @NotEmpty(message = "O campo da chave do usuário não pode ser vazio")
    private String key;

    @Column(name = "created_at", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date createdAt = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
