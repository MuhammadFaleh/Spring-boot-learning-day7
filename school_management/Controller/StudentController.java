package com.exam2.school_management.Controller;

import com.exam2.school_management.Api.ApiResponse;
import com.exam2.school_management.Model.StudentModel;
import com.exam2.school_management.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get-students")
    public ResponseEntity<?> getStudents(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @GetMapping("/search-name/{name}")
    public ResponseEntity<?> getStudentsByName(@PathVariable String name){
        StudentModel student = studentService.getStudentByName(name);

        if(student == null){
            return ResponseEntity.status(400).body(new ApiResponse("no student match that name"));
        }
        return ResponseEntity.status(200).body(student);
    }

    @GetMapping("/search-major/{major}")
    public ResponseEntity<?> getStudentsByMajor(@PathVariable String major){
        ArrayList<StudentModel> students = studentService.getStudentsByMajor(major);

        if(students.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no student have that major"));
        }
        return ResponseEntity.status(200).body(students);
    }

    @PostMapping("/create-students")
    public ResponseEntity<?> createStudents(@RequestBody @Valid StudentModel student, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        return ResponseEntity.status(200).body(new ApiResponse(studentService.createStudents(student)));
    }

    @PutMapping("/update-student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id, @RequestBody @Valid StudentModel student, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if(studentService.updateStudent(id, student)){
            return ResponseEntity.status(200).body(new ApiResponse("student updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("failed to updated student"));
    }

    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id){
        if(studentService.deleteStudent(id)){
            return ResponseEntity.status(200).body(new ApiResponse("student deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("failed to delete student"));
    }
}
