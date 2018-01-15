package rs.ac.uns.ftn.eo.students.web.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.eo.students.model.Course;
import rs.ac.uns.ftn.eo.students.model.PredajePredmet;
import rs.ac.uns.ftn.eo.students.model.Profesor;
import rs.ac.uns.ftn.eo.students.service.CourseService;
import rs.ac.uns.ftn.eo.students.service.PredajePredmetService;
import rs.ac.uns.ftn.eo.students.service.ProfesorService;
import rs.ac.uns.ftn.eo.students.web.dto.PredajePredmetDTO;

@RestController
@RequestMapping(value="api/predajepredmete")
public class PredajePredmetController {
	@Autowired
	private PredajePredmetService predajePredmetService;
	
	@Autowired
	private ProfesorService profesorService;
	
	@Autowired
	private CourseService courseService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PredajePredmetDTO>> getPredajePredmete() {
		List<PredajePredmet> predajePredmete = predajePredmetService.findAll();

		List<PredajePredmetDTO> predajePredmeteDTO = new ArrayList<>();
		for (PredajePredmet pp : predajePredmete) {
			predajePredmeteDTO.add(new PredajePredmetDTO(pp));
		}
		return new ResponseEntity<>(predajePredmeteDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PredajePredmetDTO> getPredajePredmet(@PathVariable Long id){
		PredajePredmet predajePredmet = predajePredmetService.findOne(id);
		if(predajePredmet == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new PredajePredmetDTO(predajePredmet), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<PredajePredmetDTO> savePredajePredmet(@RequestBody PredajePredmetDTO predajePredmetDTO){
		System.out.println(predajePredmetDTO);
		PredajePredmet predajePredmet = new PredajePredmet();
		predajePredmet.setUloga(predajePredmetDTO.getUloga());
		
		Course course = courseService.findOne(predajePredmetDTO.getCourse().getId());
		Profesor profesor = profesorService.findOne(predajePredmetDTO.getProfesor().getId());
		
		predajePredmet.setCourse(course);
		predajePredmet.setProfesor(profesor);
		
		predajePredmet = predajePredmetService.save(predajePredmet);
		return new ResponseEntity<>(new PredajePredmetDTO(predajePredmet), HttpStatus.CREATED);	
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<PredajePredmetDTO> updatePredajePredmet(@RequestBody PredajePredmetDTO predajePredmetDTO){
		//a course must exist
		PredajePredmet predajePredmet = predajePredmetService.findOne(predajePredmetDTO.getId()); 
		if (predajePredmet == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		predajePredmet.setUloga(predajePredmetDTO.getUloga());

		predajePredmet = predajePredmetService.save(predajePredmet);
		return new ResponseEntity<>(new PredajePredmetDTO(predajePredmet), HttpStatus.OK);	
	}
	

	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletePredajePredmet(@PathVariable Long id){
		PredajePredmet predajePredmet = predajePredmetService.findOne(id);
		if (predajePredmet != null){
			predajePredmetService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
/*	
	@RequestMapping(value="/profesor",method = RequestMethod.GET)
	public ResponseEntity<List<PredajePredmetDTO>> getProfesorPredajePredmete(@RequestBody ProfesorDTO profesorDTO) {
		Profesor profesor =  new Profesor();
		profesor.setIme(profesorDTO.getIme());
		profesor.setPrezime(profesorDTO.getPrezime());
		
		List<PredajePredmet> predajePredmete = predajePredmetService.FindByProfesor(profesorDTO);

		List<PredajePredmetDTO> predajePredmeteDTO = new ArrayList<>();
		for (PredajePredmet pp : predajePredmete) {
			predajePredmeteDTO.add(new PredajePredmetDTO(pp));
		}
		return new ResponseEntity<>(predajePredmeteDTO, HttpStatus.OK);
	}
	
*/	
	

}
