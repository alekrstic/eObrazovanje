package rs.ac.uns.ftn.eo.students.web.dto;

import rs.ac.uns.ftn.eo.students.model.Course;

public class CourseDTO {
	private Long id;
	private String naziv;
	
	public CourseDTO() {
		
	}
	
	public CourseDTO(Course course) {
		this(course.getId(), course.getNaziv());
	}
	
	public CourseDTO(Long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	public Long getId() {
		return id;
	}

	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	
}
