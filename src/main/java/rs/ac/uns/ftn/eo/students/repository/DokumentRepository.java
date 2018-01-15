package rs.ac.uns.ftn.eo.students.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.eo.students.model.DokumentaStudenta;;

public interface DokumentRepository extends JpaRepository <DokumentaStudenta,Integer>{
	
	DokumentaStudenta findOneByNaziv(String naziv);
	List<DokumentaStudenta> findAllByTip(String tip);

}
