package rs.ac.uns.ftn.eo.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.students.model.Profesor;
import rs.ac.uns.ftn.eo.students.repository.ProfesorRepository;

@Service
public class ProfesorService {
	@Autowired
	ProfesorRepository profesorRepository;
	
	public Profesor findOne(Long id) {
		return profesorRepository.findOne(id);
	}

	public List<Profesor> findAll() {
		return profesorRepository.findAll();
	}
	
	public Page<Profesor> findAll(Pageable page) {
		return profesorRepository.findAll(page);
	}

	public Profesor save(Profesor profesor) {
		return profesorRepository.save(profesor);
	}

	public void remove(Long id) {
		profesorRepository.delete(id);
	}
	
	public List<Profesor> findByIme(String ime) {
		return profesorRepository.findAllByIme(ime);
	}
	
	public List<Profesor> findByPrezime(String prezime) {
		return profesorRepository.findAllByPrezime(prezime);
	}
	
	public Profesor findByPrezimeAndIme(String prezime,String ime){
		return profesorRepository.findOneByPrezimeAndIme(prezime, ime);
	}
	
}
