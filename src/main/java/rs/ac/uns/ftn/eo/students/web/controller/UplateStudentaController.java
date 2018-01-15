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

import rs.ac.uns.ftn.eo.students.model.Student;
import rs.ac.uns.ftn.eo.students.model.UplateStudenta;
import rs.ac.uns.ftn.eo.students.service.StudentService;
import rs.ac.uns.ftn.eo.students.service.UplateStudentaService;
import rs.ac.uns.ftn.eo.students.web.dto.UplateStudentaDTO;

@RestController
@RequestMapping(value="api/uplatestudenata")
public class UplateStudentaController {
	
	@Autowired
	private UplateStudentaService uplateStudentaService;
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping(value="/all",method = RequestMethod.GET)
	public ResponseEntity<List<UplateStudentaDTO>> getUplateStudenata() {
		List<UplateStudenta> uplateStudenata = uplateStudentaService.findAll();

		List<UplateStudentaDTO> uplateStudenataDTO = new ArrayList<>();
		for (UplateStudenta us : uplateStudenata) {
			uplateStudenataDTO.add(new UplateStudentaDTO(us));
		}
		return new ResponseEntity<>(uplateStudenataDTO, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UplateStudentaDTO>> getUplateStudenataPage(Pageable page) {
		//page object holds data about pagination and sorting
		//the object is created based on the url parameters "page", "size" and "sort" 
		Page<UplateStudenta> uplateStudenata = uplateStudentaService.findAll(page);
		
		List<UplateStudentaDTO> uplateStudenataDTO = new ArrayList<>();
		for (UplateStudenta us : uplateStudenata) {
			uplateStudenataDTO.add(new UplateStudentaDTO(us));
		}
		return new ResponseEntity<>(uplateStudenataDTO, HttpStatus.OK);
	}	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UplateStudentaDTO> getUplateStudenta(@PathVariable Long id){
		UplateStudenta uplateStudenta = uplateStudentaService.findOne(id);
		if(uplateStudenta == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new UplateStudentaDTO(uplateStudenta), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<UplateStudentaDTO> saveUplateStudenta(@RequestBody UplateStudentaDTO uplateStudentaDTO){
		
		if (uplateStudentaDTO.getStudent() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Student student = studentService.findOne(uplateStudentaDTO.getStudent().getId());
		
		if (student == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		UplateStudenta uplateStudenta = new UplateStudenta();
		uplateStudenta.setDatumUplate(uplateStudentaDTO.getDatumUplate());
		uplateStudenta.setSvrhaUplate(uplateStudentaDTO.getSvrhaUplate());
		uplateStudenta.setIznosUplate(uplateStudentaDTO.getIznosUplate());
		uplateStudenta.setStudent(student);
		
		uplateStudenta = uplateStudentaService.save(uplateStudenta);
		return new ResponseEntity<>(new UplateStudentaDTO(uplateStudenta), HttpStatus.CREATED);	
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<UplateStudentaDTO> updateUplateStudenta(@RequestBody UplateStudentaDTO uplateStudentaDTO){
		//a course must exist
		UplateStudenta uplateStudenta = uplateStudentaService.findOne(uplateStudentaDTO.getId()); 
		if (uplateStudenta == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		uplateStudenta.setSvrhaUplate(uplateStudentaDTO.getSvrhaUplate());
		uplateStudenta.setDatumUplate(uplateStudentaDTO.getDatumUplate());
		uplateStudenta.setIznosUplate(uplateStudentaDTO.getIznosUplate());

		
	
		uplateStudenta = uplateStudentaService.save(uplateStudenta);
		return new ResponseEntity<>(new UplateStudentaDTO(uplateStudenta), HttpStatus.OK);	
	}
	

	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUplateStudenta(@PathVariable Long id){
		UplateStudenta uplateStudenta = uplateStudentaService.findOne(id);
		if (uplateStudenta != null){
			uplateStudentaService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

	
	
	

}
