package com.inovareti.rpontobackend.dto;

import java.util.Calendar;

public class HoraServidorDTO {

	private Long instanteServidor;
	private String timeZone;
	
	public HoraServidorDTO() {
		this.instanteServidor=Calendar.getInstance().getTimeInMillis();
		this.timeZone="";
	}

	public Long getInstanteServidor() {
		return instanteServidor;
	}

	public void setInstanteServidor(Long instanteServidor) {
		this.instanteServidor = instanteServidor;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	
	
}
