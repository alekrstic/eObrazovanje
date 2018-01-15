package rs.ac.uns.ftn.eo.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.students.model.Korisnik;
import rs.ac.uns.ftn.eo.students.repository.KorisnikRepository;

@Service
public class KorisnikService {
	@Autowired
	KorisnikRepository korisnikRepository;
	
	public Korisnik findOne(Long id) {
		return korisnikRepository.findOne(id);
	}

	public List<Korisnik> findAll() {
		return korisnikRepository.findAll();
	}
	
	public Page<Korisnik> findAll(Pageable page) {
		return korisnikRepository.findAll(page);
	}

	public Korisnik save(Korisnik korisnik) {
		return korisnikRepository.save(korisnik);
	}

	public void remove(Long id) {
		korisnikRepository.delete(id);
	}

	public Korisnik findByUsername(String username) {
		return korisnikRepository.findOneByUsername(username);
	}
}
