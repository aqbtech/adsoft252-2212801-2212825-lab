package vn.edu.hcmut.cse.adsoftweng.lab.service;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;

import java.util.List;

public interface IStudent {
    List<Student> getAllStudents();

    Student getStudentById(String id);
}
