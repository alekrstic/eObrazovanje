package rs.ac.uns.ftn.eo.students.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Korisnik {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "Username", unique = true,  nullable = false)
	String username;
	
	private String password;
	private String uloga;
	private String ime;
	private String prezime;
	
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Student student;
	
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Profesor profesor;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUloga(){
		return uloga;
	}
	public void setUloga(String uloga){
		this.uloga = uloga;
	}
	public String getIme(){
		return ime;
	}
	public void setIme(String ime){
		this.ime = ime;
	}
	public String getPrezime(){
		return prezime;
	}
	public void setPrezime(String prezime){
		this.prezime = prezime;
	}

		
}
