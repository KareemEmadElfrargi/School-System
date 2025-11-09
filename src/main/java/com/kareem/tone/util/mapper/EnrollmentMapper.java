package com.kareem.tone.util.mapper;

import com.kareem.tone.dto.EnrollmentRequestDto;
import com.kareem.tone.dto.EnrollmentResponseDto;
import com.kareem.tone.model.Course;
import com.kareem.tone.model.Enrollment;
import com.kareem.tone.model.Student;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {
    
    public Enrollment toEntity(EnrollmentRequestDto enrollmentRequestDto , Student student, Course course) {
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setStudent(student);
        enrollment.setGrade(enrollmentRequestDto.getGrade());
        return enrollment;
    }
    public EnrollmentResponseDto toDto(Enrollment enrollment) {
        return new EnrollmentResponseDto(
                enrollment.getId(),
                enrollment.getStudent().getName(),
                enrollment.getCourse().getName(),
                enrollment.getGrade()
        );
    }
}
