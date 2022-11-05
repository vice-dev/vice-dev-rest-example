package dev.vice.rest.example.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import dev.vice.rest.example.exception.controller.CustomError;
import dev.vice.rest.example.exception.controller.CustomResponseException;
import dev.vice.rest.example.model.PartialStudentEntity;
import dev.vice.rest.example.model.Student;
import dev.vice.rest.example.model.StudentFile;
import dev.vice.rest.example.repository.PartialStudentEntityRepo;
import dev.vice.rest.example.repository.StudentFileRepo;
import dev.vice.rest.example.repository.StudentRepo;
import dev.vice.rest.example.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	
	@Autowired
	StudentRepo studentRepo;
	
	@Override
	public ResponseEntity<List<Student>> findAll() {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(studentRepo.findAll());
	}
	
	@Override
	public ResponseEntity<Integer> save(Student student) {
		// TODO Auto-generated method stub
		return ResponseEntity.status(HttpStatus.CREATED).body(studentRepo.save(student).getId());
	}

	@Override
	public ResponseEntity<?> update(Student student, Integer id) {
		// TODO Auto-generated method stub
		student.setId(id);
		studentRepo.save(student);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Autowired
	PartialStudentEntityRepo partialStudentEntityRepo;
	@Override
	public ResponseEntity<?> update(PartialStudentEntity partialStudentEntity, Integer id) {
		// TODO Auto-generated method stub
		partialStudentEntity.setId(id);
		partialStudentEntityRepo.save(partialStudentEntity);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Override
	public ResponseEntity<?> delete(Integer id) {
		// TODO Auto-generated method stub
		studentRepo.deleteById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@Override
	public ResponseEntity<Student> findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Student> optionalStudent = studentRepo.findById(id);
		if (optionalStudent.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(optionalStudent.get());
	}

	@Override
	public ResponseEntity<List<Student>> findByFirsName(String firstName) {
		// TODO Auto-generated method stub
		List<Student> student = studentRepo.findByFirstName(firstName);
		if (student == null || student.isEmpty()) {
			CustomError error = new CustomError(HttpStatus.NOT_FOUND, "Student not found for first name - " + firstName);
			throw new CustomResponseException(error);
		}
		return ResponseEntity.ok(student);
	}

	@Autowired
	StudentFileRepo studentFileRepo;
	
	@Override
	public ResponseEntity<Integer> upload(MultipartFile file, Integer studentId) {
		// TODO Auto-generated method stub
		StudentFile sFile = new StudentFile();
		sFile.setStudentId(studentId);
		try {
			sFile.setFile(file.getBytes());
		} catch (IOException e) {
			CustomError error = new CustomError("Something wen wrong while uploading file!");
			throw new CustomResponseException(error);
		}
		sFile.setFileName(file.getOriginalFilename());
		return ResponseEntity.accepted().body(studentFileRepo.save(sFile).getId());
	}

	@Override
	public ResponseEntity<List<StudentFile>> findAllFiles(Integer studentId) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(studentFileRepo.findAll());
	}

	@Override
	public ResponseEntity<byte[]> findFileById(Integer studentId, Integer fileId) {
		// TODO Auto-generated method stub
		StudentFile file = studentFileRepo.findByStudentIdAndId(studentId, fileId);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
				.body(file.getFile());
	}


}
