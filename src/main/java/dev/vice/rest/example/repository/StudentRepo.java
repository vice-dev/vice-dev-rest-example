package dev.vice.rest.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.vice.rest.example.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{

	List<Student> findByFirstName(String firstName);

}
