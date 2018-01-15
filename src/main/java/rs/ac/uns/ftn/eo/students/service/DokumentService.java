package rs.ac.uns.ftn.eo.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.students.model.DokumentaStudenta;
import rs.ac.uns.ftn.eo.students.repository.DokumentRepository;

@Service
public class DokumentService {

	@Autowired
	DokumentRepository dokRepository;
	public DokumentaStudenta findOne(Integer DokumentId){
		return dokRepository.findOne(DokumentId);
	}
	public List<DokumentaStudenta> findAll(){
		return dokRepository.findAll();
	}
	public Page<DokumentaStudenta> findAll(Pageable page){
		return dokRepository.findAll(page);
	}
	public DokumentaStudenta save (DokumentaStudenta dokum){
		return dokRepository.save(dokum);
	}
	public void remove (Integer DokumentId){
		dokRepository.delete(DokumentId);
	}
	public DokumentaStudenta findOneByNaziv(String naziv){
		return dokRepository.findOneByNaziv(naziv);
	}
	public List<DokumentaStudenta> findAllByTip(String tip){
		return dokRepository.findAllByTip(tip);
	}
}
