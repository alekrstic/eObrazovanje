package rs.ac.uns.ftn.eo.students.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DokumentaStudenta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer dokumentID;
	
	// -> Student.dokumenta
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Student student;
	
	private String naziv;
	private String tip;
	private String putanjaDoDokumenta;
	
	public DokumentaStudenta() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DokumentaStudenta(Integer dokumentID, Student student, String naziv, String tip, String putanjaDoDokumenta) {
		super();
		this.dokumentID = dokumentID;
		this.student = student;
		this.naziv = naziv;
		this.tip = tip;
		this.putanjaDoDokumenta = putanjaDoDokumenta;
	}


	public Integer getDokumentID() {
		return dokumentID;
	}

	public void setDokumentID(Integer dokumentID) {
		this.dokumentID = dokumentID;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getPutanjaDoDokumenta() {
		return putanjaDoDokumenta;
	}

	public void setPutanjaDoDokumenta(String putanjaDoDokumenta) {
		this.putanjaDoDokumenta = putanjaDoDokumenta;
	}

}
