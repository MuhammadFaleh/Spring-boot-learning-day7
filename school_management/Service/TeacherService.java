package com.exam2.school_management.Service;

import com.exam2.school_management.Model.TeacherModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService {
    ArrayList<TeacherModel> teachers = new ArrayList<>();

    public ArrayList<TeacherModel> getTeachers(){
        return teachers;
    }

    public TeacherModel getTeacherByiD(String id){
        for (TeacherModel teacher: teachers){
            if(teacher.getId().equalsIgnoreCase(id)){
                return teacher;
            }
        }
        return null;
    }

    public ArrayList<TeacherModel> getTeachersBySalary(double salary){
        ArrayList<TeacherModel> matchedTeachers = new ArrayList<>();

        for (TeacherModel teacher : teachers){
            if(teacher.getSalary() >= salary){
                matchedTeachers.add(teacher);
            }
        }
        return matchedTeachers;
    }

    public String createTeacher(TeacherModel teacher){
        teachers.add(teacher);
        return "teacher created successfully";
    }

    public boolean updateTeacher(String id, TeacherModel teacher){
        for (int i = 0; i < teachers.size() ; i++) {
            if(teachers.get(i).getId().equalsIgnoreCase(id)){
                teachers.set(i, teacher);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTeacher(String id){
        for (int i = 0; i < teachers.size() ; i++) {
            if(teachers.get(i).getId().equalsIgnoreCase(id)){
                teachers.remove(i);
                return true;
            }
        }
        return false;
    }
}
