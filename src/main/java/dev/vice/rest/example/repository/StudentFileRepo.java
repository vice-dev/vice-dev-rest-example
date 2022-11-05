package dev.vice.rest.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.vice.rest.example.model.StudentFile;

@Repository
public interface StudentFileRepo extends JpaRepository<StudentFile, Integer>{

	StudentFile findByStudentIdAndId(Integer studentId, Integer id);

}
