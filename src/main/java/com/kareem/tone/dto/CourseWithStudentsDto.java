package com.kareem.tone.dto;

import com.kareem.tone.model.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
public class CourseWithStudentsDto {
    private Long id;
    private String name;
    private String description;
    private Set<String> students;
}
