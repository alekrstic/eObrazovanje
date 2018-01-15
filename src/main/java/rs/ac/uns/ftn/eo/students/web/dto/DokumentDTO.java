package rs.ac.uns.ftn.eo.students.web.dto;

import rs.ac.uns.ftn.eo.students.model.DokumentaStudenta;

public class DokumentDTO {
	
	private Integer DokumentId;
	private StudentDTO student;
	private String naziv;
	private String tip;
	private String putanjaDoDokumenta;
	
	public DokumentDTO(){
		super();
	}
public DokumentDTO(DokumentaStudenta dokument){
	this.DokumentId = dokument.getDokumentID();
	this.student = new StudentDTO(dokument.getStudent());
	this.naziv = dokument.getNaziv();
	this.tip = dokument.getTip();
	this.putanjaDoDokumenta = dokument.getPutanjaDoDokumenta();
	}
public DokumentDTO(Integer DokumentId, StudentDTO student, String naziv, String tip, String putanjaDoDokumenta) {
	super();
	this.DokumentId = DokumentId;
	this.student = student;
	this.naziv = naziv;
	this.tip = tip;
	this.putanjaDoDokumenta = putanjaDoDokumenta;
}
public Integer getDokumentId() {
	return DokumentId;
}
public void setDokumentId(Integer dokumentId) {
	DokumentId = dokumentId;
}
public StudentDTO getStudent() {
	return student;
}
public void setStudent(StudentDTO student) {
	this.student = student;
}
public String getNaziv() {
	return naziv;
}
public void setNaziv(String naziv) {
	this.naziv = naziv;
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

