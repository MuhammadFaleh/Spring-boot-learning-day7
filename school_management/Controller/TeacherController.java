package com.exam2.school_management.Controller;

import com.exam2.school_management.Api.ApiResponse;
import com.exam2.school_management.Model.StudentModel;
import com.exam2.school_management.Model.TeacherModel;
import com.exam2.school_management.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/get-teachers")
    public ResponseEntity<?> getTeachers(){
        ArrayList<TeacherModel> teachers = teacherService.getTeachers();

        if(teachers.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no teachers have been created"));
        }
        return ResponseEntity.status(200).body(teachers);
    }

    @GetMapping("/search-id/{id}")
    public ResponseEntity<?> getTeachersByName(@PathVariable String id){
        TeacherModel teacher = teacherService.getTeacherByiD(id);

        if(teacher == null){
            return ResponseEntity.status(400).body(new ApiResponse("no teacher have that id"));
        }
        return ResponseEntity.status(200).body(teacher);
    }

    @GetMapping("/search-salary/{salary}")
    public ResponseEntity<?> getTeachersBySalary(@PathVariable double salary){
        ArrayList<TeacherModel> teachers = teacherService.getTeachersBySalary(salary);

        if(teachers.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no teachers have that salary"));
        }
        return ResponseEntity.status(200).body(teachers);
    }

    @PostMapping("/create-teachers")
    public ResponseEntity<?> createStudents(@RequestBody @Valid TeacherModel teacher, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        return ResponseEntity.status(200).body(new ApiResponse(teacherService.createTeacher(teacher)));
    }

    @PutMapping("/update-teacher/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable String id, @RequestBody @Valid TeacherModel teacher, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if(teacherService.updateTeacher(id, teacher)){
            return ResponseEntity.status(200).body(new ApiResponse("teacher was updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("failed to updated teacher"));
    }

    @DeleteMapping("/delete-teacher/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable String id){
        if(teacherService.deleteTeacher(id)){
            return ResponseEntity.status(200).body(new ApiResponse("teacher deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("failed to delete teacher"));
    }
}
