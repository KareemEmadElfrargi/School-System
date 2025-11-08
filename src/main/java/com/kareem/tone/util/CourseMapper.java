package com.kareem.tone.util;

import com.kareem.tone.dto.CourseRequestDto;
import com.kareem.tone.dto.CourseResponseDto;
import com.kareem.tone.model.Course;
import org.springframework.stereotype.Component;

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
}
