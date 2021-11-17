package com.inovareti.rpontobackend.dto;

import java.io.Serializable;

public class AjusteParaAprovarDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	String[] id;
	String status;
	
	AjusteParaAprovarDTO(){
		
	}

	public AjusteParaAprovarDTO(String[] id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
