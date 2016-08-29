package br.fatec.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_user")
	private Long id;
	
	@Column(name = "email_user", unique=true, length = 60, nullable = false)
	private String email;
	
	@Column(name = "api_key", unique=true, length = 60, nullable = false)
	private String key;
	
	@Column(name="created_at", nullable = false)
	private Date createdAt;
	

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

	public void setCreated_at(Date createdAt) {
		this.createdAt = createdAt;
	}

}
