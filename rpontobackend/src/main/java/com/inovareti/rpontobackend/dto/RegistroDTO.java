package com.inovareti.rpontobackend.dto;

import java.io.Serializable;
import java.util.Date;

import com.inovareti.rpontobackend.domain.Registro;

public class RegistroDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private Date dateRegistro;
	private Long instante;
	private String tipoRegistro;
	private String funcionarioId;
	private String funcionarioNome;
	private String EmpresaNome;
	private String EmpresaId;
	
	public RegistroDTO(Registro obj) {
		this.id = obj.getId();
		this.dateRegistro=obj.getDateRegistro();
		this.EmpresaId=obj.getFuncionario().getEmpresa().getId();
		this.EmpresaNome=obj.getFuncionario().getEmpresa().getNome();
		this.funcionarioId=obj.getFuncionario().getId();
		this.funcionarioNome=obj.getFuncionario().getNome();
		this.instante=obj.getInstante();
		this.tipoRegistro=obj.getTipoRegistro();
		}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateRegistro() {
		return dateRegistro;
	}

	public void setDateRegistro(Date dateRegistro) {
		this.dateRegistro = dateRegistro;
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

	public String getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(String funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	public String getFuncionarioNome() {
		return funcionarioNome;
	}

	public void setFuncionarioNome(String funcionarioNome) {
		this.funcionarioNome = funcionarioNome;
	}

	public String getEmpresaNome() {
		return EmpresaNome;
	}

	public void setEmpresaNome(String empresaNome) {
		EmpresaNome = empresaNome;
	}

	public String getEmpresaId() {
		return EmpresaId;
	}

	public void setEmpresaId(String empresaId) {
		EmpresaId = empresaId;
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
		RegistroDTO other = (RegistroDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
