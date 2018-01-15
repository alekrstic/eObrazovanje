package rs.ac.uns.ftn.eo.students.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//import com.google.common.io.Files;

import rs.ac.uns.ftn.eo.students.model.DokumentaStudenta;
import rs.ac.uns.ftn.eo.students.model.Student;
import rs.ac.uns.ftn.eo.students.service.DokumentService;
import rs.ac.uns.ftn.eo.students.service.StudentService;
import rs.ac.uns.ftn.eo.students.web.dto.DokumentDTO;


@RestController
@RequestMapping(value="api/dokument")
public class DokumentController {
	
	private static final Logger LOG = Logger.getLogger(DokumentController.class);
	
	@Autowired
	private DokumentService dokumentService;
	@Autowired 
	private StudentService studentService;
	
	@RequestMapping (method = RequestMethod.GET)
	public ResponseEntity<List<DokumentDTO>>getDokuments(){
		List<DokumentaStudenta> dokument = dokumentService.findAll();
		//convert document to DTOs
		List<DokumentDTO> dokumentDTO = new ArrayList<>();
		for (DokumentaStudenta d: dokument){
			dokumentDTO.add(new DokumentDTO(d));
		}
		return new ResponseEntity<>(dokumentDTO,HttpStatus.OK);	
	}
	
	@RequestMapping (value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<DokumentDTO> getDokument(@PathVariable Integer DokumentId){
		DokumentaStudenta dokument = dokumentService.findOne(DokumentId);
		if(dokument == null){
			return new ResponseEntity<>(new DokumentDTO(dokument), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new DokumentDTO(dokument), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<DokumentDTO> saveDokument(
  			@RequestParam(value="naziv", required=true) String naziv,
  			@RequestParam(value="tip", required=true) String tip,
  			@RequestParam(value="id", required=true) String id,
  			@RequestParam(value="file", required=true) MultipartFile file
			){
		try {
			DokumentaStudenta dokument = new DokumentaStudenta();
			Student student = studentService.findOne(Long.parseLong(id));
			
			//priprema nove putanje
		    String fileFolderName = UUID.randomUUID().toString();
		    String folderPath = "static/uploads/dokumenti/" + fileFolderName + "/";
		    //jedinstveni folder
		    File fileFolder = new File(folderPath);
		    fileFolder.mkdir();
		    
			String putanjaDoDokumenta = folderPath+naziv;
			
			dokument.setPutanjaDoDokumenta(putanjaDoDokumenta);
	  		dokument.setNaziv(naziv);
			dokument.setTip(tip);
			dokument.setStudent(student);
			
			dokumentService.save(dokument);
	  		
			//cuvanje novog fajla
			String filePath = new File(putanjaDoDokumenta).getAbsolutePath();
			File dest = new File(filePath);
	        file.transferTo(dest);
			
			return new ResponseEntity<>(new DokumentDTO(dokument), HttpStatus.CREATED);
		} catch (RuntimeException e) {
	  		  LOG.error("Error while uploading.", e);
	          throw e;
	  		} catch (Exception e) {
	          LOG.error("Error while uploading.", e);
	          throw new RuntimeException(e);
	  		}
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<DokumentDTO> updateDokument(@RequestBody DokumentDTO dokumentDTO){
		//a dokument must exist
		DokumentaStudenta dokument = dokumentService.findOne(dokumentDTO.getDokumentId()); 
		if (dokument == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		dokument.setDokumentID(dokumentDTO.getDokumentId());
		dokument.setNaziv(dokumentDTO.getNaziv());
		dokument.setPutanjaDoDokumenta(dokumentDTO.getPutanjaDoDokumenta());
		dokument.setTip(dokumentDTO.getTip());
		
	
		dokument = dokumentService.save(dokument);
		return new ResponseEntity<>(new DokumentDTO(dokument), HttpStatus.OK);	
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteDokument(@PathVariable Integer DokumentId){
		DokumentaStudenta dokument = dokumentService.findOne(DokumentId);
		if (dokument != null){
			dokumentService.remove(DokumentId);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
}
	

	
		
		

