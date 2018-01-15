package rs.ac.uns.ftn.eo.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.eo.students.model.Student;
import rs.ac.uns.ftn.eo.students.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	public Student findOne(Long id) {
		return studentRepository.findOne(id);
	}

	public List<Student> findAll() {
		return studentRepository.findAll();
	}
	
	public Page<Student> findAll(Pageable page) {
		return studentRepository.findAll(page);
	}

	public Student save(Student student) {
		return studentRepository.save(student);
	}

	public void remove(Long id) {
		studentRepository.delete(id);
	}
	
	public Student findByCard(String brIndexa) {
		return studentRepository.findOneByBrIndexa(brIndexa);
	}
	
	public List<Student> findByPrezime(String prezime) {
		return studentRepository.findAllByPrezime(prezime);
	}
	
	public Student findByPrezimeAndIme(String prezime,String ime){
		return studentRepository.findOneByPrezimeAndIme(prezime, ime);
	}
}
