package rs.ac.uns.ftn.eo.students.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.eo.students.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Student findOneByBrIndexa(String brIndexa);
    List<Student> findAllByPrezime(String prezime);
    Student findOneByPrezimeAndIme(String prezime, String ime);
}
