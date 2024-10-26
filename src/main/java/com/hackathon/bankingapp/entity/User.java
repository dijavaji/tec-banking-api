package com.hackathon.bankingapp.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USER")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USERID",nullable=false, unique=true)
	private Integer id;
	
	@NotEmpty(message ="no puede estar vacio")
	@NotNull(message= "no puede ser nulo")
	@Column(name="NAME",nullable=false)
	private String name; //atributo nombre
	
	@NotEmpty(message ="no puede estar vacio")
	@Size(min=4, message = "debe tener una longitud minima de 5")
	@Column(name="PASS",nullable=false)
	private String password;
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(name="ADDRESS")
	private String address;// atributo direccion
	
	@NotEmpty(message ="no puede estar vacio")
	@Email(message="no es una direccion de correo bien formada")
	@Column(name="EMAIL",nullable=false, unique=true)
	private String email;// email
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(name="PHONE",nullable=false, unique=true)
	private String phoneNumber;// telefono
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(name="CREATEDBY",nullable=false)
	private String createdBy;
	
	@Column(name="CREATEDDATE",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(name="MODIFIEDBY")
	private String modifiedBy;
	
	@Column(name="MODIFIEDDATE")
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;
	
	@Column(name="STATUS")
	private Boolean status;
	
	@ManyToOne
	@JoinColumn(name = "USERTYPEID", nullable = false)
	private UserType userType;
	
	@PrePersist 
	public void prePersist() {
		createdDate = new Date();
		status = Boolean.TRUE;
	}
}
