package rs.ac.uns.ftn.eo.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.eo.students.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
