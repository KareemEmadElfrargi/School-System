package com.kareem.tone.controller;

import com.kareem.tone.dto.CourseRequestDto;
import com.kareem.tone.dto.CourseResponseDto;
import com.kareem.tone.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseResponseDto> getAllCourses(){
        return courseService.getAllCourses();
    }
    @GetMapping("/id")
    public CourseResponseDto getCourseById(@RequestParam long id){
        return courseService.getCourseById(id);
    }
    @PostMapping()
    public CourseResponseDto addCourse(@RequestBody CourseRequestDto courseRequestDto){
        return courseService.addCourse(courseRequestDto);
    }
    @PutMapping("/id")
    public CourseResponseDto updateCourse(@PathVariable Long id,@RequestBody CourseRequestDto courseRequestDto){
        return courseService.updateCourse(id, courseRequestDto);
    }
    @DeleteMapping("/id")
    public CourseResponseDto deleteCourse(@PathVariable Long id){
        return courseService.deleteCourse(id);
    }
}
