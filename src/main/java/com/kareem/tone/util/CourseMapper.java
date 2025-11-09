package com.kareem.tone.util;

import com.kareem.tone.dto.CourseRequestDto;
import com.kareem.tone.dto.CourseResponseDto;
import com.kareem.tone.dto.CourseWithStudentsDto;
import com.kareem.tone.model.Course;
import com.kareem.tone.model.Student;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequestDto courseRequestDto){
        Course course = new Course();
        course.setName(courseRequestDto.getName());
        course.setDescription(courseRequestDto.getDescription());
        return course;
    }

    public CourseResponseDto toDto(Course course){
        CourseResponseDto courseResponseDto = new CourseResponseDto();
        courseResponseDto.setId(course.getId());
        courseResponseDto.setName(course.getName());
        courseResponseDto.setDescription(course.getDescription());
        return courseResponseDto;
    }

    public CourseWithStudentsDto toDtoWithStudents(Course course){
        Set<String> studentNames = course.getStudents()
                .stream()
                .map(Student::getName)
                .collect(Collectors.toSet());

        return new CourseWithStudentsDto(
                course.getId(),
                course.getName(),
                course.getDescription(),
                studentNames
        );
    }
}
