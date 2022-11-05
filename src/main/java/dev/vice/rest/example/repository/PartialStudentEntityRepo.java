package dev.vice.rest.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.vice.rest.example.model.PartialStudentEntity;

@Repository
public interface PartialStudentEntityRepo extends JpaRepository<PartialStudentEntity, Integer>{

}
