package com.kareem.tone.repository;


import com.kareem.tone.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);

    // Search by name with pagination
    Page<Student> findByNameContainingIgnoreCase(String name , Pageable pageable);

}
