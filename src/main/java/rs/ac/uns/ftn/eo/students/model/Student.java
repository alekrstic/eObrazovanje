package rs.ac.uns.ftn.eo.students.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Student {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "BrIndexa", unique = true, nullable = false)
	String brIndexa;
	
	private String ime;
	
	private String prezime;
	
	private String adresa;
	
	private String mail;
	
	private int godina;
	
	private int semestar;
	
	private String budzet;
	
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Enrollment> enrollments = new HashSet<Enrollment>();
	
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<PredajePredmet> predajePredmet = new HashSet<PredajePredmet>();
	
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<PolaganjeIspita> ispiti = new HashSet<PolaganjeIspita>();
	
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<UplateStudenta> uplate = new HashSet<UplateStudenta>();
	
	@OneToOne(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Korisnik korisnik;
	
	
	
	public Student(String brIndexa, String ime, String prezime, String mail, int godina, int semestar, String budzet,
			Set<Enrollment> enrollments, Set<PredajePredmet> predajePredmet,
			Set<PolaganjeIspita> ispiti, Set<UplateStudenta> uplate) {
		super();
		this.brIndexa = brIndexa;
		this.ime = ime;
		this.prezime = prezime;
		this.mail = mail;
		this.godina = godina;
		this.semestar = semestar;
		this.budzet = budzet;
		this.enrollments = enrollments;
		this.predajePredmet = predajePredmet;
		this.ispiti = ispiti;
		this.uplate = uplate;
	}
	
	
	
	public Student() {
		// TODO Auto-generated constructor stub
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getBrIndexa() {
		return brIndexa;
	}

	public void setBrIndexa(String brIndexa) {
		this.brIndexa = brIndexa;
	}
	
	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public int getSemestar() {
		return semestar;
	}

	public void setSemestar(int semestar) {
		this.semestar = semestar;
	}

	public String getBudzet() {
		return budzet;
	}

	public void setBudzet(String budzet) {
		this.budzet = budzet;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}



	public Set<PolaganjeIspita> getIspit() {
		return ispiti;
	}



	public void setIspit(Set<PolaganjeIspita> ispiti) {
		this.ispiti = ispiti;
	}



	public Set<PredajePredmet> getPredajePredmet() {
		return predajePredmet;
	}



	public void setPredajePredmet(Set<PredajePredmet> predajePredmet) {
		this.predajePredmet = predajePredmet;
	}



	public Set<PolaganjeIspita> getIspiti() {
		return ispiti;
	}



	public void setIspiti(Set<PolaganjeIspita> ispiti) {
		this.ispiti = ispiti;
	}



	public Set<UplateStudenta> getUplate() {
		return uplate;
	}



	public void setUplate(Set<UplateStudenta> uplate) {
		this.uplate = uplate;
	}


	
}
