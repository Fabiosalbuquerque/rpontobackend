package com.inovareti.rpontobackend.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.inovareti.rpontobackend.domain.Registro;

public class RegistroDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private Date dateRegistro;
	private Long instante;
	private Long instanteServidor;
	private Integer diaRegistro;
	private Integer mesRegistro;
	private Integer anoRegistro;
	@NotEmpty
	private String tipoRegistro;
	@NotEmpty
	private String funcionarioId;
	private String funcionarioNome;
	private String funcionarioEmail;
	private String EmpresaNome;
	@NotEmpty
	private String EmpresaId;
	private String ajustado;
	private Date dateRegistroAjustado;
	private Long instanteAjustado;
	
	public RegistroDTO(Registro obj) {
		this.id = obj.getId();
		this.dateRegistro=obj.getDateRegistro();
		this.EmpresaId=obj.getEmpresa().getId();
		this.EmpresaNome=obj.getEmpresa().getNome();
		this.funcionarioId=obj.getFuncionario().getId();
		this.funcionarioNome=obj.getFuncionario().getNome();
		this.funcionarioEmail = obj.getFuncionario().getEmail();
		this.instante=obj.getInstante();
		this.tipoRegistro=obj.getTipoRegistro();
		this.diaRegistro=obj.getDiaRegistro();
		this.mesRegistro=obj.getMesRegistro();
		this.anoRegistro=obj.getAnoRegistro();
		this.ajustado=obj.getAjustado();
		this.dateRegistroAjustado=obj.getDateRegistroAjustado();
		this.instanteAjustado = obj.getInstanteAjustado();
		this.instanteServidor = Calendar.getInstance().getTimeInMillis();
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

	public Long getInstanteServidor() {
		return instanteServidor;
	}

	public void setInstanteServidor(Long instanteServidor) {
		this.instanteServidor = instanteServidor;
	}

	public Integer getDiaRegistro() {
		return diaRegistro;
	}

	public void setDiaRegistro(Integer dia_registro) {
		this.diaRegistro = dia_registro;
	}

	public Integer getMesRegistro() {
		return mesRegistro;
	}

	public void setMesRegistro(Integer mes_registro) {
		this.mesRegistro = mes_registro;
	}

	public Integer getAnoRegistro() {
		return anoRegistro;
	}

	public void setAnoRegistro(Integer ano_registro) {
		this.anoRegistro = ano_registro;
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

	public String getFuncionarioEmail() {
		return funcionarioEmail;
	}

	public void setFuncionarioEmail(String funcionarioEmail) {
		this.funcionarioEmail = funcionarioEmail;
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

	public String getAjustado() {
		return ajustado;
	}

	public void setAjustado(String ajustado) {
		this.ajustado = ajustado;
	}

	public Date getDateRegistroAjustado() {
		return dateRegistroAjustado;
	}

	public void setDateRegistroAjustado(Date dateRegistroAjustado) {
		this.dateRegistroAjustado = dateRegistroAjustado;
	}

	public Long getInstanteAjustado() {
		return instanteAjustado;
	}

	public void setInstanteAjustado(Long instanteAjustado) {
		this.instanteAjustado = instanteAjustado;
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
