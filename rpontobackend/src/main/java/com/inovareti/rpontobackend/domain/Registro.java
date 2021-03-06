package com.inovareti.rpontobackend.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inovareti.rpontobackend.dto.EmpresaDTO;
import com.inovareti.rpontobackend.dto.FuncionarioDTO;

@Document(collection="registros")
public class Registro implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Date dateRegistro;
	private Long instante;
	private String tipoRegistro;
	private FuncionarioDTO funcionario;
	private EmpresaDTO empresa;
	public Registro() {
		
	}
	
	public Registro(String id, Long instante, String tipoRegistro, Funcionario funcionario) {
		super();
		this.id = id;
		this.instante = instante;
		this.dateRegistro = new Date(instante);
		this.tipoRegistro = tipoRegistro;
		this.funcionario = new FuncionarioDTO(funcionario);
		this.empresa = new EmpresaDTO(funcionario.getEmpresa());
	}
	
	public Registro(String id, Long instante, String tipoRegistro, FuncionarioDTO funcionario,EmpresaDTO empresa) {
		super();
		this.id = id;
		this.instante = instante;
		this.dateRegistro = new Date(instante);
		this.tipoRegistro = tipoRegistro;
		this.funcionario = funcionario;
		this.empresa = empresa;
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

	public Date getDateRegistro() {
		return dateRegistro;
	}

	public void setDateRegistro(Date dateRegistro) {
		this.dateRegistro = dateRegistro;
	}
	
	public void setDateRegistroFromInstante() {
		Calendar aux = Calendar.getInstance(new Locale("pt","BR"));
		TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
		TimeZone.setDefault(tz);
		aux.setTimeZone(tz);
		aux.setTimeInMillis(this.instante);
		//System.out.println("TimeZone atual é : "+aux.getTimeZone());
		//System.out.println("Date/Time atual é : "+aux.getTime());
		//System.out.println("milisegundos atual é : "+aux.getTimeInMillis());
		
		aux.add(Calendar.HOUR, -3);
		this.dateRegistro = aux.getTime();
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
	}
	
	public void setFuncionario(Funcionario func) {
		if(null!=func) {
		this.funcionario.setEmail(func.getEmail());
		this.funcionario.setId(func.getId());
		this.funcionario.setNome(func.getNome());
		}
	}

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa.setCnpj(empresa.getCnpj());
		this.empresa.setId(empresa.getId());
		this.empresa.setNome(empresa.getNome());
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
