package rs.ac.uns.ftn.eo.students.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Profesor {
	
	@Id
	@GeneratedValue
	private Long id;
	private String ime;	
	private String prezime;	
	private String uloga;	
	private String mail;
	
	@OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<PredajePredmet> predajePredmet = new HashSet<PredajePredmet>();
	
	@OneToOne(mappedBy = "profesor", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Korisnik korisnik;

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Set<PredajePredmet> getPredajePredmet() {
		return predajePredmet;
	}

	public void setPredajePredmet(Set<PredajePredmet> predajePredmet) {
		this.predajePredmet = predajePredmet;
	}
	

}
