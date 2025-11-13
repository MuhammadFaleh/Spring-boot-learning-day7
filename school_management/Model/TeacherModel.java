package com.exam2.school_management.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeacherModel {
    @NotBlank(message = "teacher id should not be empty")
    @Size(min = 4, max=4, message = "teacher id should be exactly 4 characters long")
    @Pattern(regexp = "^[0-9]*$", message = "teacher id should only be numbers")
    private String id;
    @NotBlank(message = "name should not be empty")
    @Size(min=3, max = 40, message = "name should be between 3 and 40")
    private String name;
    @NotNull(message = "salary should not be empty")
    @Positive(message = "salary should be a positive number")
    @Min(value = 1000, message = "salary can't be lower than 1000")
    private double salary;
}
