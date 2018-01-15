package rs.ac.uns.ftn.eo.students.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Course {
	@Id
	@GeneratedValue
	private Long id;
	
	String naziv;
	
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Enrollment> enrollments = new HashSet<Enrollment>();
	
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<PredajePredmet> predajePredmet = new HashSet<PredajePredmet>();
	
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<PolaganjeIspita> ispit = new HashSet<PolaganjeIspita>();
	
	
	
	
	
	public Course(){}
	
	public Course(Long id, String naziv, Set<Enrollment> enrollments, Set<PredajePredmet> predajePredmet, Set<PolaganjeIspita> ispit) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.enrollments = enrollments;
		this.predajePredmet = predajePredmet;
		this.ispit = ispit;
	}

	public Set<PredajePredmet> getPredajePredmet() {
		return predajePredmet;
	}

	public void setPredajePredmet(Set<PredajePredmet> predajePredmet) {
		this.predajePredmet = predajePredmet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public Set<PolaganjeIspita> getIspit() {
		return ispit;
	}

	public void setIspit(Set<PolaganjeIspita> ispit) {
		this.ispit = ispit;
	}

	
}
