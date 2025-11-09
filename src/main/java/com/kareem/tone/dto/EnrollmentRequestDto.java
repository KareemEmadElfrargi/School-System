package com.kareem.tone.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnrollmentRequestDto {
    private Long studentId;
    private Long courseId;
    private Double grade;
}
