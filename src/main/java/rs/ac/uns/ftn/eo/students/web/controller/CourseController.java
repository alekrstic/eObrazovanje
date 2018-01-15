package rs.ac.uns.ftn.eo.students.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.eo.students.model.Course;
import rs.ac.uns.ftn.eo.students.model.Enrollment;
import rs.ac.uns.ftn.eo.students.model.PolaganjeIspita;
import rs.ac.uns.ftn.eo.students.model.PredajePredmet;
import rs.ac.uns.ftn.eo.students.service.CourseService;
import rs.ac.uns.ftn.eo.students.web.dto.CourseDTO;
import rs.ac.uns.ftn.eo.students.web.dto.EnrollmentDTO;
import rs.ac.uns.ftn.eo.students.web.dto.PolaganjeIspitaDTO;
import rs.ac.uns.ftn.eo.students.web.dto.PredajePredmetDTO;
import rs.ac.uns.ftn.eo.students.web.dto.ProfesorDTO;
import rs.ac.uns.ftn.eo.students.web.dto.StudentDTO;

@RestController
@RequestMapping(value="api/courses")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value="/all",method = RequestMethod.GET)
	public ResponseEntity<List<CourseDTO>> getCourses() {
		List<Course> courses = courseService.findAll();
		//convert courses to DTOs
		List<CourseDTO> coursesDTO = new ArrayList<>();
		for (Course s : courses) {
			coursesDTO.add(new CourseDTO(s));
		}
		return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CourseDTO>> getCoursesPage(Pageable page) {
		//page object holds data about pagination and sorting
		//the object is created based on the url parameters "page", "size" and "sort" 
		Page<Course> courses = courseService.findAll(page);
		
		List<CourseDTO> coursesDTO = new ArrayList<>();
		for (Course c : courses) {
			coursesDTO.add(new CourseDTO(c));
		}
		return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
	}	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<CourseDTO> getCourse(@PathVariable Long id){
		Course course = courseService.findOne(id);
		if(course == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new CourseDTO(course), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<CourseDTO> saveCourse(@RequestBody CourseDTO courseDTO){
		Course course = new Course();
		course.setNaziv(courseDTO.getNaziv());
	
		course = courseService.save(course);
		return new ResponseEntity<>(new CourseDTO(course), HttpStatus.CREATED);	
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO){
		//a course must exist
		Course course = courseService.findOne(courseDTO.getId()); 
		if (course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		course.setNaziv(courseDTO.getNaziv());
	
		course = courseService.save(course);
		return new ResponseEntity<>(new CourseDTO(course), HttpStatus.OK);	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id){
		Course course = courseService.findOne(id);
		if (course != null){
			courseService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{courseId}/students", method = RequestMethod.GET)
	public ResponseEntity<List<EnrollmentDTO>> getCourseStudents(
			@PathVariable Long courseId) {
		Course course = courseService.findOne(courseId);
		Set<Enrollment> enrollments = course.getEnrollments();
		List<EnrollmentDTO> enrollmentsDTO = new ArrayList<>();
		for (Enrollment e: enrollments) {
			EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
			enrollmentDTO.setId(e.getId());
			enrollmentDTO.setStartDate(e.getStartDate());
			enrollmentDTO.setEndDate(e.getEndDate());
			enrollmentDTO.setStudent(new StudentDTO(e.getStudent()));
			//we leave course field empty
			
			enrollmentsDTO.add(enrollmentDTO);
		}
		return new ResponseEntity<>(enrollmentsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{courseId}/profesors", method = RequestMethod.GET)
	public ResponseEntity<List<PredajePredmetDTO>> getProfesors(
			@PathVariable Long courseId) {
		Course course = courseService.findOne(courseId);
		Set<PredajePredmet> predajePredmete = course.getPredajePredmet();
		List<PredajePredmetDTO> predajePredmeteDTO = new ArrayList<>();
		for (PredajePredmet e: predajePredmete) {
			PredajePredmetDTO predajePredmetDTO = new PredajePredmetDTO();
			predajePredmetDTO.setId(e.getId());
			predajePredmetDTO.setUloga(e.getUloga());
			predajePredmetDTO.setProfesor(new ProfesorDTO(e.getProfesor()));
			//we leave course field empty
			
			predajePredmeteDTO.add(predajePredmetDTO);
		}
		return new ResponseEntity<>(predajePredmeteDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{courseId}/ispiti", method = RequestMethod.GET)
	public ResponseEntity<List<PolaganjeIspitaDTO>> getIspiti(
			@PathVariable Long courseId) {
		Course course = courseService.findOne(courseId);
		Set<PolaganjeIspita> polaganjaIspita = course.getIspit();
		List<PolaganjeIspitaDTO> polaganjaIspitaDTO = new ArrayList<>();
		for (PolaganjeIspita pi: polaganjaIspita) {
			PolaganjeIspitaDTO polaganjeIspitaDTO = new PolaganjeIspitaDTO();
			polaganjeIspitaDTO.setId(pi.getId());
			polaganjeIspitaDTO.setOcena(pi.getOcena());
			polaganjeIspitaDTO.setPolozen(pi.isPolozen());
			polaganjeIspitaDTO.setStudent(new StudentDTO(pi.getStudent()));
			polaganjeIspitaDTO.setCourse(new CourseDTO(course));
			//we leave course field empty
			
			polaganjaIspitaDTO.add(polaganjeIspitaDTO);
		}
		return new ResponseEntity<>(polaganjaIspitaDTO, HttpStatus.OK);
	}
	
}
