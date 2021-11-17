package com.inovareti.rpontobackend.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ajustes")
public class Ajuste implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
    private String data;
    private String hora;
    private Integer dia;
    private Integer mes;
    private Integer ano;
    private String email;
    private String tipoRegistro;
    private Long instanteRegistro;
    private String justificativa;
    private String status;
    private String approvadoPor;
    private String requisitadoPor;
    private Long requisicaoTimeStamp;
	
	
    public Ajuste() {
    	
    }
    
    
    
    
	public Ajuste(String id, String data, String hora, Integer dia, Integer mes, Integer ano,
			String email, String tipoRegistro, String justificativa, String status, String approvadoPor,
			String requisitadoPor) {
		super();
		this.id = id;
		this.data = data;
		this.hora = hora;
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.email = email;
		this.tipoRegistro = tipoRegistro;
		this.justificativa = justificativa;
		this.status = status;
		this.approvadoPor = approvadoPor;
		this.requisitadoPor = requisitadoPor;
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getData() {
		return data;
	}




	public void setData(String data) {
		this.data = data;
	}




	public String getHora() {
		return hora;
	}




	public void setHora(String hora) {
		this.hora = hora;
	}




	public Integer getDia() {
		return dia;
	}




	public void setDia(Integer dia) {
		this.dia = dia;
	}




	public Integer getMes() {
		return mes;
	}




	public void setMes(Integer mes) {
		this.mes = mes;
	}




	public Integer getAno() {
		return ano;
	}




	public void setAno(Integer ano) {
		this.ano = ano;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getTipoRegistro() {
		return tipoRegistro;
	}




	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}




	public Long getInstanteRegistro() {
		return instanteRegistro;
	}




	public void setInstanteRegistro(Long instanteRegistro) {
		this.instanteRegistro = instanteRegistro;
	}




	public String getJustificativa() {
		return justificativa;
	}




	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getApprovadoPor() {
		return approvadoPor;
	}




	public void setApprovadoPor(String approvadoPor) {
		this.approvadoPor = approvadoPor;
	}




	public String getRequisitadoPor() {
		return requisitadoPor;
	}




	public void setRequisitadoPor(String requisitadoPor) {
		this.requisitadoPor = requisitadoPor;
	}




	public Long getRequisicaoTimeStamp() {
		return requisicaoTimeStamp;
	}




	public void setRequisicaoTimeStamp(Long requisicaoTimeStamp) {
		this.requisicaoTimeStamp = requisicaoTimeStamp;
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
		Ajuste other = (Ajuste) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}