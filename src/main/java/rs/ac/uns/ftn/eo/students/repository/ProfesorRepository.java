package rs.ac.uns.ftn.eo.students.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.eo.students.model.Profesor;


public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
	Profesor findOneByIme(String ime);
    List<Profesor> findAllByIme (String ime);
	List<Profesor> findAllByPrezime(String prezime);
    Profesor findOneByPrezimeAndIme(String prezime, String ime);
}

