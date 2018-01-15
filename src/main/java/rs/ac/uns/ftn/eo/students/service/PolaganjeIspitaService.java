package rs.ac.uns.ftn.eo.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.students.model.PolaganjeIspita;
import rs.ac.uns.ftn.eo.students.repository.PolaganjeIspitaRepository;

@Service
public class PolaganjeIspitaService {
	@Autowired
	PolaganjeIspitaRepository polaganjeIspitaRepository;
	
	public PolaganjeIspita findOne(Long id) {
		return polaganjeIspitaRepository.findOne(id);
	}

	public List<PolaganjeIspita> findAll() {
		return polaganjeIspitaRepository.findAll();
	}
	
	public Page<PolaganjeIspita> findAll(Pageable page) {
		return polaganjeIspitaRepository.findAll(page);
	}

	public PolaganjeIspita save(PolaganjeIspita polaganjeIspita) {
		return polaganjeIspitaRepository.save(polaganjeIspita);
	}

	public void remove(Long id) {
		polaganjeIspitaRepository.delete(id);
	}
}
