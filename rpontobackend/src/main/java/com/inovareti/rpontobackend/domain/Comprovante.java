package com.inovareti.rpontobackend.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="comprovantes")
public class Comprovante implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	String id;
	String nomeFuncionario;
	String idFuncionario;
	String pisFuncionario;
	String tipoComprovanteRegistro;
	Date dtHoraRegistro;
	Integer diaRegistro;
	Integer mesRegistro;
	Integer anoRegistro;
	Long instanteRegistro;
	String hashComprovante;
	String nomeEmpresa;
	String cnpj;
	
	public Comprovante(){
		
	}
	public Comprovante(Registro registro){
		this.id=null;
		this.nomeFuncionario=registro.getFuncionario().getNome();
		this.idFuncionario=registro.getFuncionario().getId();
		this.anoRegistro=registro.getAnoRegistro();
		this.diaRegistro=registro.getDiaRegistro();
		this.mesRegistro=registro.getMesRegistro();
		this.instanteRegistro=registro.getInstante();
		this.cnpj=registro.getEmpresa().getCnpj();
		this.nomeEmpresa=registro.getEmpresa().getNome();
		this.dtHoraRegistro=registro.getDateRegistro();
		this.tipoComprovanteRegistro=registro.getTipoRegistro();
		this.hashComprovante="";
	}

	public Comprovante(String id, String nomeFuncionario, String idFuncionario, Date dtHoraRegistro,
			Integer diaRegistro, Integer mesRegistro, Integer anoRegistro, Long instanteRegistro,
			String hashComprovante, String cnpj) {
		super();
		this.id = id;
		this.nomeFuncionario = nomeFuncionario;
		this.idFuncionario = idFuncionario;
		this.dtHoraRegistro = dtHoraRegistro;
		this.diaRegistro = diaRegistro;
		this.mesRegistro = mesRegistro;
		this.anoRegistro = anoRegistro;
		this.instanteRegistro = instanteRegistro;
		this.hashComprovante = hashComprovante;
		this.cnpj = cnpj;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String funcionario) {
		this.idFuncionario = funcionario;
	}

	public Date getDtHoraRegistro() {
		return dtHoraRegistro;
	}

	public String getPisFuncionario() {
		return pisFuncionario;
	}
	public void setPisFuncionario(String pisFuncionario) {
		this.pisFuncionario = pisFuncionario;
	}
	public String getTipoComprovanteRegistro() {
		return tipoComprovanteRegistro;
	}
	public void setTipoComprovanteRegistro(String tipoComprovanteRegistro) {
		this.tipoComprovanteRegistro = tipoComprovanteRegistro;
	}
	public void setDtHoraRegistro(Date dtHoraRegistro) {
		this.dtHoraRegistro = dtHoraRegistro;
	}

	public Integer getDiaRegistro() {
		return diaRegistro;
	}

	public void setDiaRegistro(Integer diaRegistro) {
		this.diaRegistro = diaRegistro;
	}

	public Integer getMesRegistro() {
		return mesRegistro;
	}

	public void setMesRegistro(Integer mesRegistro) {
		this.mesRegistro = mesRegistro;
	}

	public Integer getAnoRegistro() {
		return anoRegistro;
	}

	public void setAnoRegistro(Integer anoRegistro) {
		this.anoRegistro = anoRegistro;
	}

	public Long getInstanteRegistro() {
		return instanteRegistro;
	}

	public void setInstanteRegistro(Long instanteRegistro) {
		this.instanteRegistro = instanteRegistro;
	}

	public String getHashComprovante() {
		return hashComprovante;
	}

	public void setHashComprovante(String hashComprovante) {
		this.hashComprovante = hashComprovante;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String empresa) {
		this.cnpj = empresa;
	}
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comprovante other = (Comprovante) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
