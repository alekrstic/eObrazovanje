package rs.ac.uns.ftn.eo.students.web.dto;

import rs.ac.uns.ftn.eo.students.model.PredajePredmet;

public class PredajePredmetDTO {
	
	private Long id;
	private String uloga;
	private ProfesorDTO profesor;
	private CourseDTO course;
	
	public PredajePredmetDTO() {
		
	}
	
	public PredajePredmetDTO (PredajePredmet predajePredmet) {
		id = predajePredmet.getId();
		uloga = predajePredmet.getUloga();
		profesor = new ProfesorDTO(predajePredmet.getProfesor());
		course = new CourseDTO(predajePredmet.getCourse());
	}
	
	

	public ProfesorDTO getProfesor() {
		return profesor;
	}

	public void setProfesor(ProfesorDTO profesor) {
		this.profesor = profesor;
	}

	public CourseDTO getCourse() {
		return course;
	}

	public void setCourse(CourseDTO course) {
		this.course = course;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public Long getId() {
		return id;
	}
	
	public String getUloga() {
		return uloga;
	}


}
