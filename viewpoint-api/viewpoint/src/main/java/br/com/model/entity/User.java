package br.com.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    @JsonProperty
    private Long id;

    @Column(name = "email_user", unique = true, nullable = false)
    @Length(max = 254)
    @JsonProperty
    @NotEmpty(message = "O campo email não deveria ser vazio")
    @Email
    private String email;

    @Column(name = "password_user", nullable = false)
    @Length(min = 6, max = 254)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty(message = "O campo de senha não deveria ser vazio")
    private String password;

    @Column(name = "name_user", nullable = false)
    @JsonProperty
    @Length(max = 60)
    @NotEmpty(message = "O campo de nome não deveria ser vazio")
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
