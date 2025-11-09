package com.kareem.tone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EnrollmentResponseDto {
    private Long id;
    private String studentName;
    private String courseName;
    private Double grade;
}
