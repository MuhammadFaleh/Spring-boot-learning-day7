package com.exam2.school_management.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentModel {
    @NotBlank(message = "student id should not be empty")
    @Size(min = 4, max = 4, message = "student id should be exactly 4")
    @Pattern(regexp = "^[0-9]*$", message = "student id should only be numbers")
    private String id;
    @NotBlank(message = "student name should not be empty")
    @Size(min=3, max = 40, message = "name should be between 3 and 40")
    private String name;
    @NotNull(message = "age should not be empty")
    @Min(value = 10, message = "age should not younger than 10")
    private int age;
    @NotBlank(message = "student major should not be empty")
    @Pattern(regexp = "^(?i)(Computer Science|english|Engineering|mathematics)$",
            message = "'Computer Science' , 'english' 'Engineering' or 'mathematics'")
    private String major;

}
