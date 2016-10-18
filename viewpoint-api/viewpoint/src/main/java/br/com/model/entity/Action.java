package br.com.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "actions")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_action")
    @JsonProperty
    private Long id;

    @Column(name = "name_action", unique = true, length = 60, nullable = false)
    @JsonProperty
    private String name;

    @Column(name = "created_at", nullable = false)
    @JsonProperty
    private Date createdAt = new Date();
    

    @Column(name = "group_action", unique = true, length = 60, nullable = false)
    @JsonProperty
    private String group;

    @Column(name = "parameters", unique = true, length = 100, nullable = false)
    @JsonProperty
    private String parameters;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_usu", nullable = true)
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
}
