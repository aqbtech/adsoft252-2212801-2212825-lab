package vn.edu.hcmut.cse.adsoftweng.lab.service;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;

import java.util.List;

public interface IStudent {
    List<Student> getAllStudents();

    Student getStudentById(String id);

    List<Student> searchByName(String keyword);

    Student save(Student student);

    Student update(String id, Student student);

    void delete(String id);
}
