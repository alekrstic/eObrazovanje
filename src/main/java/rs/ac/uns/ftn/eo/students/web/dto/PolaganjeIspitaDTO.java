package rs.ac.uns.ftn.eo.students.web.dto;


import rs.ac.uns.ftn.eo.students.model.PolaganjeIspita;

public class PolaganjeIspitaDTO {
	
	private long id;
	private boolean polozen;
	private int ocena;
	private StudentDTO student;
	private CourseDTO course;
	
	public PolaganjeIspitaDTO() {
		
	}
	
	public PolaganjeIspitaDTO (PolaganjeIspita polaganjeIspita) {

		id = polaganjeIspita.getId();
		polozen = polaganjeIspita.isPolozen();
		ocena = polaganjeIspita.getOcena();
		student = new StudentDTO (polaganjeIspita.getStudent());
		course = new CourseDTO (polaganjeIspita.getCourse());
	}
	

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isPolozen() {
		return polozen;
	}
	
	public void setPolozen(boolean polozen) {
		this.polozen = polozen;
	}
	
	public int getOcena(){
		return ocena;
	}
	
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	
	public StudentDTO getStudent() {
		return student;
	}
	
	public CourseDTO getCourse() {
		return course;
	}
	
	public void setStudent(StudentDTO student) {
		this.student = student;
	}
	
	public void setCourse(CourseDTO course) {
		this.course = course;
	}
	
	

}
