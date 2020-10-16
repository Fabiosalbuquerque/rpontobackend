package com.inovareti.rpontobackend.dto;

import java.io.Serializable;

import com.inovareti.rpontobackend.domain.Empresa;

public class EmpresaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	private String cnpj;
	
	public EmpresaDTO() {
		
	}
	
	
	
	public EmpresaDTO(String id, String nome, String cnpj) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
	}



	public EmpresaDTO(Empresa obj) {
		this.id=obj.getId();
		this.nome=obj.getNome();
		this.cnpj=obj.getCnpj();
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
