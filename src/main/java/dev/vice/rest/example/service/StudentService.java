package dev.vice.rest.example.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.vice.rest.example.model.PartialStudentEntity;
import dev.vice.rest.example.model.Student;
import dev.vice.rest.example.model.StudentFile;

@Service
public interface StudentService {
	
	public ResponseEntity<List<Student>> findAll();

	public ResponseEntity<Integer> save(Student student);

	public ResponseEntity<?> update(Student student, Integer id);

	public ResponseEntity<?> update(PartialStudentEntity partialStudentEntity, Integer id);

	public ResponseEntity<?> delete(Integer id);

	public ResponseEntity<Student> findById(Integer id);

	public ResponseEntity<List<Student>> findByFirsName(String firstName);

	public ResponseEntity<Integer> upload(MultipartFile file, Integer id);

	public ResponseEntity<List<StudentFile>> findAllFiles(Integer studentId);

	public ResponseEntity<byte[]> findFileById(Integer studentId, Integer fileId);


}
