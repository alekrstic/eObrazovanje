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

import rs.ac.uns.ftn.eo.students.model.Korisnik;
import rs.ac.uns.ftn.eo.students.service.KorisnikService;
import rs.ac.uns.ftn.eo.students.web.dto.KorisnikDTO;

@RestController
@RequestMapping(value="api/korisnici")
public class KorisnikController {
	@Autowired
	private KorisnikService korisnikService;
	
	@RequestMapping(value="/all",method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> getKorisnici() {
		List<Korisnik> korisnici = korisnikService.findAll();
		//convert users to DTOs
		List<KorisnikDTO> korisniciDTO = new ArrayList<>();
		for (Korisnik k : korisnici) {
			korisniciDTO.add(new KorisnikDTO(k));
		}
		return new ResponseEntity<>(korisniciDTO, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> getKorisniciPage(Pageable page) {
		//page object holds data about pagination and sorting
		//the object is created based on the url parameters "page", "size" and "sort" 
		Page<Korisnik> korisnici = korisnikService.findAll(page);
		
		List<KorisnikDTO> korisniciDTO = new ArrayList<>();
		for (Korisnik k : korisnici) {
			korisniciDTO.add(new KorisnikDTO(k));
		}
		return new ResponseEntity<>(korisniciDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> getKorisnik(@PathVariable Long id){
		Korisnik korisnik = korisnikService.findOne(id);
		if(korisnik == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new KorisnikDTO(korisnik), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<KorisnikDTO> saveKorisnik(@RequestBody KorisnikDTO korisnikDTO){
		Korisnik korisnik = new Korisnik();
		korisnik.setUsername(korisnikDTO.getUsername());
		korisnik.setPassword(korisnikDTO.getPassword());
		korisnik.setUloga(korisnikDTO.getUloga());
		korisnik.setIme(korisnikDTO.getIme());
		korisnik.setPrezime(korisnikDTO.getPrezime());
	
		korisnik = korisnikService.save(korisnik);
		return new ResponseEntity<>(new KorisnikDTO(korisnik), HttpStatus.CREATED);	
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<KorisnikDTO> updateKorisnik(@RequestBody KorisnikDTO korisnikDTO){
		//a korisnik must exist
		Korisnik korisnik = korisnikService.findOne(korisnikDTO.getId()); 
		if (korisnik == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		korisnik.setPassword(korisnikDTO.getPassword());
		korisnik.setUsername(korisnikDTO.getUsername());
		korisnik.setUloga(korisnikDTO.getUloga());
		korisnik.setIme(korisnikDTO.getIme());
		korisnik.setPrezime(korisnikDTO.getPrezime());
	
		korisnik = korisnikService.save(korisnik);
		return new ResponseEntity<>(new KorisnikDTO(korisnik), HttpStatus.OK);	
	}
	

	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteKorisnik(@PathVariable Long id){
		Korisnik korisnik = korisnikService.findOne(id);
		if (korisnik != null){
			korisnikService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/findUsername", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<KorisnikDTO> getKorisnikByUsername(
			@RequestBody KorisnikDTO userPass){
		Korisnik korisnik = korisnikService.findByUsername(userPass.getUsername());
		if(korisnik == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	
		if (!(korisnik.getPassword().equals(userPass.getPassword()))){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new KorisnikDTO(korisnik), HttpStatus.OK);
	}
	
	

}
