package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.service.IStudent;

import java.util.List;

@Controller
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private IStudent iStudent;

    @GetMapping
    public String getAllStudents(Model model) {
        List<Student> students = iStudent.getAllStudents();

        model.addAttribute("dsSinhVien", students);

        return "students";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        return ResponseEntity.ok(iStudent.getStudentById(id));
    }
}
