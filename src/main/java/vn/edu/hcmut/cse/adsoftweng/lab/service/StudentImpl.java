package vn.edu.hcmut.cse.adsoftweng.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.repository.StudentRepository;

import java.util.List;

@Service
public class StudentImpl implements IStudent {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(String id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> searchByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(String id, Student student) {
        Student existing = studentRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setAge(student.getAge());
        return studentRepository.save(existing);
    }

    @Override
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}
