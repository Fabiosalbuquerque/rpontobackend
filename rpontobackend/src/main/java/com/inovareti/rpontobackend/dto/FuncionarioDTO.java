package com.inovareti.rpontobackend.dto;

import java.io.Serializable;

import com.inovareti.rpontobackend.domain.Funcionario;

public class FuncionarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	private String email;
	private String pis;
	private String aprovadorEmail;
	public FuncionarioDTO(){}
	
	
	
	public FuncionarioDTO(String id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}



	public FuncionarioDTO(Funcionario obj){
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.pis = obj.getPis();
		this.aprovadorEmail= obj.getAprovadorEmail();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPis() {
		return pis;
	}



	public void setPis(String pis) {
		this.pis = pis;
	}



	public String getAprovadorEmail() {
		return aprovadorEmail;
	}



	public void setAprovadorEmail(String aprovadorEmail) {
		this.aprovadorEmail = aprovadorEmail;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuncionarioDTO other = (FuncionarioDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
