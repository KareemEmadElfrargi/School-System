package com.kareem.tone.service;

import com.kareem.tone.model.Student;
import com.kareem.tone.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Email already in use: " + student.getEmail());
        }
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student with id: " + id + " not found"));

        // check email when update
        if (!student.getEmail().equals(updatedStudent.getEmail())
                && studentRepository.existsByEmail(updatedStudent.getEmail())){
            throw new RuntimeException("Email already in use: " + updatedStudent.getEmail());
        }

        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.setAge(updatedStudent.getAge());
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student with id: " + id + " not found");
        }
        studentRepository.deleteById(id);
    }

    public Student getStudentById(long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student with id: " + id + " not found"));
    }

    public Page<Student> searchStudents(String name , Pageable pageable){
        if (name == null || name.trim().isEmpty()){
            return studentRepository.findAll(pageable);
        }
        return studentRepository.findByNameContainingIgnoreCase(name,pageable);
    }

}
