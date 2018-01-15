package rs.ac.uns.ftn.eo.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.eo.students.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

}
