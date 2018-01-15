package rs.ac.uns.ftn.eo.students.web.dto;

import java.util.Date;


import rs.ac.uns.ftn.eo.students.model.UplateStudenta;

public class UplateStudentaDTO {
	
	private Long id;
	
	private Date datumUplate;
	
	private String svrhaUplate;
	
	private int iznosUplate;
	
	private StudentDTO student;
	
	public UplateStudentaDTO() {
		
	}
	
	public UplateStudentaDTO (UplateStudenta uplateStudenta) {
		id = uplateStudenta.getId();
		datumUplate = uplateStudenta.getDatumUplate();
		svrhaUplate = uplateStudenta.getSvrhaUplate();
		iznosUplate = uplateStudenta.getIznosUplate();
		student = new StudentDTO(uplateStudenta.getStudent());
	}

	public Long getId() {
		return id;
	}
	
	public String getSvrhaUplate() {
		return svrhaUplate;
	}
	
	public Date getDatumUplate() {
		return datumUplate;
	}

	public int getIznosUplate() {
		return iznosUplate;
	}

	public void setIznosUplate(int iznosUplate) {
		this.iznosUplate = iznosUplate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDatumUplate(Date datumUplate) {
		this.datumUplate = datumUplate;
	}

	public void setSvrhaUplate(String svrhaUplate) {
		this.svrhaUplate = svrhaUplate;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

	

	
	

}
