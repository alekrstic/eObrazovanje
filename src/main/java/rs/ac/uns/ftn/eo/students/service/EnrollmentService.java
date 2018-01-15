package rs.ac.uns.ftn.eo.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.students.model.Enrollment;
import rs.ac.uns.ftn.eo.students.repository.EnrollmentRepository;

@Service
public class EnrollmentService {
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	public Enrollment findOne(Long id) {
		return enrollmentRepository.findOne(id);
	}

	public List<Enrollment> findAll() {
		return enrollmentRepository.findAll();
	}
	
	public Page<Enrollment> findAll(Pageable page) {
		return enrollmentRepository.findAll(page);
	}
	
	public Enrollment save(Enrollment enrollment) {
		return enrollmentRepository.save(enrollment);
	}

	public void remove(Long id) {
		enrollmentRepository.delete(id);
	}
}
