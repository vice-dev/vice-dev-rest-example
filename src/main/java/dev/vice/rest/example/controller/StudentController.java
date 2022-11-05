package dev.vice.rest.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.vice.rest.example.model.PartialStudentEntity;
import dev.vice.rest.example.model.Student;
import dev.vice.rest.example.model.StudentFile;
import dev.vice.rest.example.service.StudentService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@GetMapping(produces = "application/vnd.students.api.v1+json")
	public ResponseEntity<List<Student>> findAll(){
		return studentService.findAll();
	}
	
	@GetMapping(produces = "application/vnd.students.api.v2+json")
	public CollectionModel<Student> findAllV2(){
		ResponseEntity<List<Student>> response = studentService.findAll();
		for(final Student student : response.getBody()) {
			// self link
			Link selfLink = linkTo(StudentController.class).slash(student.getId()).withSelfRel();
			student.add(selfLink);
			// find by name link
			Link findByNameLink = linkTo(methodOn(StudentController.class).findByFirstName(student.getFirstName())).withRel("findByFirstName");
			student.add(findByNameLink);
		}
		Link link = linkTo(StudentController.class).withSelfRel();
		CollectionModel<Student> result = CollectionModel.of(response.getBody(), link);
		return result;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> findById(@PathVariable(name = "id") Integer id) {
		return studentService.findById(id);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Student>> findByFirstName(@RequestParam(name = "first-name") String firstName) {
		return studentService.findByFirsName(firstName);
	}

	@PostMapping
	public ResponseEntity<Integer> save(@RequestBody Student student) {
		return studentService.save(student);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable(name = "id") Integer id,
			@RequestBody Student student) {
		return studentService.update(student, id);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable(name = "id") Integer id,
			@RequestBody PartialStudentEntity partialStudentEntity) {
		return studentService.update(partialStudentEntity, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable(name = "id") Integer id) {
		return studentService.delete(id);
	}
	
	
	@PutMapping("/{studentId}/files")
	public ResponseEntity<Integer> uploadFile(@PathVariable(name = "studentId") Integer studentId,
			@RequestParam MultipartFile file){
		return studentService.upload(file, studentId);
	}
	
	@GetMapping("/{studentId}/files")
	public ResponseEntity<List<StudentFile>> findAllFiles(@PathVariable(name = "studentId") Integer studentId){
		return studentService.findAllFiles(studentId);
	}
	
	@GetMapping("/{studentId}/files/{fileId}")
	public ResponseEntity<byte[]> findFileById(@PathVariable(name = "studentId") Integer studentId,
			@PathVariable(name = "fileId") Integer fileId){
		return studentService.findFileById(studentId, fileId);
	}
	

}
