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
	
	@Column(name = "email_usu", unique=true, length = 60, nullable = false)
	private String email;
	
	@Column(name = "api_key", unique=true, length = 60, nullable = false)
	private String key;
	
	@Column(name="created_at", nullable = false)
	private Date created_at = new Date();
	
	@Column(name="created_at")
	private Date updated_at;

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

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

}
