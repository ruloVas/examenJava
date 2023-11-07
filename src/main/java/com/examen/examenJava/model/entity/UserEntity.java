package com.examen.examenJava.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity{
	
    @Id
	@SequenceGenerator(name="USERS_SEQ" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERS_SEQ")
    private Long id;
    
    @NotBlank
    @Size(min=3, max = 50)
    private String name;
    
    @NotBlank
    @Size(min=3, max = 50)
    private String username;
    
    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    @NotBlank
    @Size(min=6, max = 100)
    private String password;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
    	joinColumns = @JoinColumn(name = "user_id"), 
    	inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

	public UserEntity(@NotBlank @Size(min = 3, max = 50) String name,
			@NotBlank @Size(min = 3, max = 50) String username, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(min = 6, max = 100) String password) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}

}
