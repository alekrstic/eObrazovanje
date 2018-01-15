package rs.ac.uns.ftn.eo.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.eo.students.model.Korisnik;


public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

	Korisnik findOneByUsername(String username);

}