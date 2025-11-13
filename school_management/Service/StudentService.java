package com.exam2.school_management.Service;

import com.exam2.school_management.Model.StudentModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {
    ArrayList<StudentModel> students = new ArrayList<>();


    public ArrayList<StudentModel> getStudents(){
        return students;
    }

    public StudentModel getStudentByName(String name){
        for (StudentModel student : students){
            if(student.getName().equalsIgnoreCase(name)){
                return student;
            }
        }
        return null;
    }

    public ArrayList<StudentModel> getStudentsByMajor(String major){
        ArrayList<StudentModel> matchedStudents = new ArrayList<>();
        for (StudentModel student: students){
            if(student.getMajor().equalsIgnoreCase(major)){
                matchedStudents.add(student);
            }
        }
        return matchedStudents;
    }

    public String createStudents(StudentModel student){
        students.add(student);
        return "student created successfully";
    }

    public boolean updateStudent(String id, StudentModel student){
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getId().equalsIgnoreCase(id)){
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String id){
        for (int i = 0; i < students.size() ; i++) {
            if(students.get(i).getId().equalsIgnoreCase(id)){
                students.remove(i);
                return true;
            }
        }
        return false;
    }

}
