package com.kareem.tone.service;

import com.kareem.tone.dto.StudentRequestDto;
import com.kareem.tone.dto.StudentResponseDto;
import com.kareem.tone.model.Student;
import com.kareem.tone.repository.StudentRepository;
import com.kareem.tone.util.StudentMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDto addStudent(StudentRequestDto requestDto) {
        if (studentRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("Email already in use: " + requestDto.getEmail());
        }
        Student student = studentMapper.toEntity(requestDto);
        Student saved = studentRepository.save(student);
        return studentMapper.toDTO(saved);
    }

    public StudentResponseDto updateStudent(Long id, StudentRequestDto requestDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student with id: " + id + " not found"));

        // check email when update
        if (!student.getEmail().equals(requestDto.getEmail())
                && studentRepository.existsByEmail(requestDto.getEmail())){
            throw new RuntimeException("Email already in use: " + requestDto.getEmail());
        }

        student.setName(requestDto.getName());
        student.setEmail(requestDto.getEmail());
        student.setAge(requestDto.getAge());
        Student updated = studentRepository.save(student);

        return studentMapper.toDTO(updated);
    }

    public void deleteStudent(long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student with id: " + id + " not found");
        }
        studentRepository.deleteById(id);
    }

    public StudentResponseDto getStudentById(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student with id: " + id + " not found"));
      return studentMapper.toDTO(student);
    }

    public Page<StudentResponseDto> searchStudents(String name , Pageable pageable){
        Page<Student> pageResult;
        if (name == null || name.trim().isEmpty()){
            pageResult = studentRepository.findAll(pageable);
        }else {
            pageResult = studentRepository.findByNameContainingIgnoreCase(name,pageable);
        }
        return pageResult.map(studentMapper::toDTO);
    }

}
