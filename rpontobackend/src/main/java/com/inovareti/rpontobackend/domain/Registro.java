package com.inovareti.rpontobackend.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection="registros")
public class Registro implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Long instante;
	private String tipoRegistro;
	@DBRef
	private Funcionario funcionario;
	
	public Registro(String id, Long instante, String tipoRegistro, Funcionario funcionario) {
		super();
		this.id = id;
		this.instante = instante;
		this.tipoRegistro = tipoRegistro;
		this.funcionario = funcionario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getInstante() {
		return instante;
	}

	public void setInstante(Long instante) {
		this.instante = instante;
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
		Registro other = (Registro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
