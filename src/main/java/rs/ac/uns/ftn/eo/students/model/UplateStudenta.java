package rs.ac.uns.ftn.eo.students.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UplateStudenta {
	
	@Id
	@GeneratedValue
	
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "datumUplate", unique = false, nullable = false)
	private Date datumUplate;
	
	@Column(name = "svrhaUplate", unique = false, nullable = false)
	private String svrhaUplate;
	
	@Column(name = "iznosUplate", unique = false, nullable = false)
	private int iznosUplate;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Student student;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDatumUplate() {
		return datumUplate;
	}
	public void setDatumUplate(Date datumUplate) {
		this.datumUplate = datumUplate;
	}
	public String getSvrhaUplate() {
		return svrhaUplate;
	}
	public void setSvrhaUplate(String svrhaUplate) {
		this.svrhaUplate = svrhaUplate;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public int getIznosUplate() {
		return iznosUplate;
	}
	public void setIznosUplate(int iznosUplate) {
		this.iznosUplate = iznosUplate;
	}
	
	

}
