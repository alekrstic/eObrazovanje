package rs.ac.uns.ftn.eo.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.students.model.UplateStudenta;
import rs.ac.uns.ftn.eo.students.repository.UplateStudentaRepository;

@Service
public class UplateStudentaService {
	@Autowired
	UplateStudentaRepository uplateStudentaRepository;
	
	public UplateStudenta findOne(Long id) {
		return uplateStudentaRepository.findOne(id);
	}

	public List<UplateStudenta> findAll() {
		return uplateStudentaRepository.findAll();
	}
	
	public Page<UplateStudenta> findAll(Pageable page) {
		return uplateStudentaRepository.findAll(page);
	}

	public UplateStudenta save(UplateStudenta uplateStudenta) {
		return uplateStudentaRepository.save(uplateStudenta);
	}

	public void remove(Long id) {
		uplateStudentaRepository.delete(id);
	}
}
