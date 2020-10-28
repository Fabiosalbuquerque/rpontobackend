package com.inovareti.rpontobackend.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.inovareti.rpontobackend.enums.Perfil;



public class UserSS implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	private Boolean locked;
	
	public UserSS() {
		
	}
	
	
	
	public UserSS(String id, String email, String senha,Boolean Locked, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.locked = Locked;
		this.authorities = perfis.stream().map(x-> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	public String getid() {
		return id;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}
	public boolean hasHole(Perfil perfil) {
		 return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
