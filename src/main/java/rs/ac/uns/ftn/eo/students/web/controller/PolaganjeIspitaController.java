package rs.ac.uns.ftn.eo.students.web.controller;

import java.util.ArrayList;
import java.util.List;

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
import rs.ac.uns.ftn.eo.students.model.PolaganjeIspita;
import rs.ac.uns.ftn.eo.students.model.Student;
import rs.ac.uns.ftn.eo.students.service.CourseService;
import rs.ac.uns.ftn.eo.students.service.PolaganjeIspitaService;
import rs.ac.uns.ftn.eo.students.service.StudentService;
import rs.ac.uns.ftn.eo.students.web.dto.CourseDTO;
import rs.ac.uns.ftn.eo.students.web.dto.PolaganjeIspitaDTO;
import rs.ac.uns.ftn.eo.students.web.dto.StudentDTO;


@RestController
@RequestMapping(value="api/polaganjaispita")
public class PolaganjeIspitaController {
	@Autowired
	private PolaganjeIspitaService polaganjeIspitaService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	CourseService courseService;
	
	@RequestMapping(value="/all",method = RequestMethod.GET)
	public ResponseEntity<List<PolaganjeIspitaDTO>> getPolaganjaIspita() {
		List<PolaganjeIspita> polaganjaIspita = polaganjeIspitaService.findAll();

		List<PolaganjeIspitaDTO> polaganjaIspitaDTO = new ArrayList<>();
		for (PolaganjeIspita pi : polaganjaIspita) {
			polaganjaIspitaDTO.add(new PolaganjeIspitaDTO(pi));
		}
		return new ResponseEntity<>(polaganjaIspitaDTO, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PolaganjeIspitaDTO>> getPolaganjaIspitaPage(Pageable page) {
		//page object holds data about pagination and sorting
		//the object is created based on the url parameters "page", "size" and "sort" 
		Page<PolaganjeIspita> polaganjaIspita = polaganjeIspitaService.findAll(page);
		
		List<PolaganjeIspitaDTO> polaganjaIspitaDTO = new ArrayList<>();
		for (PolaganjeIspita pi : polaganjaIspita) {
			polaganjaIspitaDTO.add(new PolaganjeIspitaDTO(pi));
		}
		return new ResponseEntity<>(polaganjaIspitaDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PolaganjeIspitaDTO> getPolaganjeIspita(@PathVariable Long id){
		PolaganjeIspita polaganjeIspita = polaganjeIspitaService.findOne(id);
		if(polaganjeIspita == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new PolaganjeIspitaDTO(polaganjeIspita), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<PolaganjeIspitaDTO> savePolaganjeIspita(@RequestBody PolaganjeIspitaDTO polaganjeIspitaDTO){
		
		if (polaganjeIspitaDTO.getStudent() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Student student = studentService.findOne(polaganjeIspitaDTO.getStudent().getId());
		
		if (student == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (polaganjeIspitaDTO.getCourse() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Course course = courseService.findOne(polaganjeIspitaDTO.getCourse().getId());
		
		if (course == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		PolaganjeIspita polaganjeIspita = new PolaganjeIspita();
		polaganjeIspita.setOcena(polaganjeIspitaDTO.getOcena());
		polaganjeIspita.setPolozen(polaganjeIspitaDTO.isPolozen());
		polaganjeIspita.setStudent(student);
		polaganjeIspita.setCourse(course);
	
		polaganjeIspita = polaganjeIspitaService.save(polaganjeIspita);
		return new ResponseEntity<>(new PolaganjeIspitaDTO(polaganjeIspita), HttpStatus.CREATED);	
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<PolaganjeIspitaDTO> updatePolaganjeIspita(@RequestBody PolaganjeIspitaDTO polaganjeIspitaDTO){
		PolaganjeIspita polaganjeIspita = polaganjeIspitaService.findOne(polaganjeIspitaDTO.getId()); 
		if (polaganjeIspita == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		polaganjeIspita.setOcena(polaganjeIspita.getOcena());
	
		polaganjeIspita = polaganjeIspitaService.save(polaganjeIspita);
		return new ResponseEntity<>(new PolaganjeIspitaDTO(polaganjeIspita), HttpStatus.OK);	
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletePolaganjeIspita(@PathVariable Long id){
		PolaganjeIspita polaganjeIspita = polaganjeIspitaService.findOne(id);
		if (polaganjeIspita != null){
			polaganjeIspitaService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{examId}/students", method = RequestMethod.GET)
	public ResponseEntity<List<StudentDTO>> getStudents(
			@PathVariable Long examId) {
		PolaganjeIspita exam = polaganjeIspitaService.findOne(examId);
		Student student = exam.getStudent();
		
		List<StudentDTO> studentsDTO = new ArrayList<>();

			StudentDTO studentDTO = new StudentDTO();
			studentDTO.setAdresa(student.getAdresa());
			studentDTO.setBrIndexa(student.getBrIndexa());
			studentDTO.setBudzet(student.getBudzet());
			studentDTO.setGodina(student.getGodina());
			studentDTO.setId(student.getId());
			studentDTO.setIme(student.getIme());
			studentDTO.setPrezime(student.getPrezime());
			studentDTO.setMail(student.getMail());
			studentDTO.setSemestar(student.getSemestar());
			
			studentsDTO.add(studentDTO);
		
		return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{examId}/courses", method = RequestMethod.GET)
	public ResponseEntity<List<CourseDTO>> getCourses(
			@PathVariable Long examId) {
		PolaganjeIspita exam = polaganjeIspitaService.findOne(examId);
		Course course = exam.getCourse();
		
		List<CourseDTO> coursesDTO = new ArrayList<>();

			CourseDTO courseDTO = new CourseDTO();
			courseDTO.setNaziv(course.getNaziv());

			
			coursesDTO.add(courseDTO);
		
		return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
	}
	
	

}
