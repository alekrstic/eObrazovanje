package rs.ac.uns.ftn.eo.students.web.dto;

import rs.ac.uns.ftn.eo.students.model.Profesor;

public class ProfesorDTO {

	private Long id;	
	private String ime;	
	private String prezime;	
	private String uloga;	
	private String mail;
	
	public ProfesorDTO() {
		
	}
	
	public ProfesorDTO (Profesor profesor) {
		this(profesor.getId(), profesor.getIme(), profesor.getPrezime(), profesor.getUloga(), profesor.getMail());
	}
	
	public ProfesorDTO (Long id, String ime, String prezime, String uloga, String mail) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.uloga = uloga;
		this.mail = mail;
	}

	public Long getId() {
		return id;
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public String getUloga() {
		return uloga;
	}

	public String getMail() {
		return mail;
	}


}

