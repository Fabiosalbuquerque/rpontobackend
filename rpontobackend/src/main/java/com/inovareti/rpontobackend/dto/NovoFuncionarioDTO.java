package com.inovareti.rpontobackend.dto;

import java.io.Serializable;

public class NovoFuncionarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private String empresaId;
	private String senha;
	private String enderecoLogradouro;
	private String enderecoBairro;
	private String enderecoNumero;
	private String enderecoComplemento;
	private String enderecoCidade;
	private String enderecoCEP;
	
	public NovoFuncionarioDTO() {
		
	}

	public NovoFuncionarioDTO(String nome, String email, String cpfOuCnpj, String empresaId, String senha,
			String enderecoLogradouro, String enderecoBairro, String enderecoNumero, String enderecoComplemento,
			String enderecoCidade, String enderecoCEP) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.empresaId = empresaId;
		this.senha = senha;
		this.enderecoLogradouro = enderecoLogradouro;
		this.enderecoBairro = enderecoBairro;
		this.enderecoNumero = enderecoNumero;
		this.enderecoComplemento = enderecoComplemento;
		this.enderecoCidade = enderecoCidade;
		this.enderecoCEP = enderecoCEP;
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public String getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(String empresaId) {
		this.empresaId = empresaId;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEnderecoLogradouro() {
		return enderecoLogradouro;
	}

	public void setEnderecoLogradouro(String enderecoLogradouro) {
		this.enderecoLogradouro = enderecoLogradouro;
	}

	public String getEnderecoBairro() {
		return enderecoBairro;
	}

	public void setEnderecoBairro(String enderecoBairro) {
		this.enderecoBairro = enderecoBairro;
	}

	public String getEnderecoNumero() {
		return enderecoNumero;
	}

	public void setEnderecoNumero(String enderecoNumero) {
		this.enderecoNumero = enderecoNumero;
	}

	public String getEnderecoComplemento() {
		return enderecoComplemento;
	}

	public void setEnderecoComplemento(String enderecoComplemento) {
		this.enderecoComplemento = enderecoComplemento;
	}

	public String getEnderecoCidade() {
		return enderecoCidade;
	}

	public void setEnderecoCidade(String enderecoCidade) {
		this.enderecoCidade = enderecoCidade;
	}

	public String getEnderecoCEP() {
		return enderecoCEP;
	}

	public void setEnderecoCEP(String enderecoCEP) {
		this.enderecoCEP = enderecoCEP;
	}
	
}
