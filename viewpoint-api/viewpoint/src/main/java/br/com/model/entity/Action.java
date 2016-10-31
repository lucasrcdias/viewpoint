package br.com.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "actions")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Action implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_action")
    @JsonProperty
    private Long id;

    @Column(name = "name_action")
    @JsonProperty
    @Length(max = 60, message = "O nome do evento deve conter no máximo 60 caracteres")
    @NotEmpty(message = "O preenchimento do nome do evento é obrigatório")
    private String name;

    @Column(name = "created_at", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date createdAt = new Date();


    @Column(name = "group_action")
    @JsonProperty
    @Length(max = 60, message = "O grupo do evento deve conter no máximo 60 caracteres")
    @NotEmpty(message = "O preenchimento do grupo do evento é obrigatório")
    private String group;

    @Column(name = "parameters")
    @Length(max = 400, message = "O parametros devem conter no máximo 400 caracteres")
    @JsonProperty
    private String parameters;

    @Column(name = "ip_action")
    @JsonProperty
    @Length(max = 18, message = "O ip que enviou o evento deve conter no máximo 18 caracteres")
    private String ipAddress;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_usu")
    @JsonProperty
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
