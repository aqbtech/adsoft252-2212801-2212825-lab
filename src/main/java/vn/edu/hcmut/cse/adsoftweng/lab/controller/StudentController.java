package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.service.IStudent;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private IStudent iStudent;

    // 1. Trang Danh Sách - GET /students
    @GetMapping
    public String getAllStudents(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Student> students;
        if (keyword != null && !keyword.trim().isEmpty()) {
            students = iStudent.searchByName(keyword.trim());
        } else {
            students = iStudent.getAllStudents();
        }
        model.addAttribute("dsSinhVien", students);
        model.addAttribute("keyword", keyword);
        return "students";
    }

    // 2. Trang Chi Tiết - GET /students/{id}
    @GetMapping("/{id}")
    public String getStudentDetail(@PathVariable String id, Model model) {
        Student student = iStudent.getStudentById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "student-detail";
    }

    // 3. Trang Form Thêm Mới - GET /students/new
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("isEdit", false);
        return "student-form";
    }

    // 4. Xử lý Thêm Mới - POST /students
    @PostMapping
    public String createStudent(@ModelAttribute Student student) {
        iStudent.createStudent(student);
        return "redirect:/students";
    }

    // 5. Trang Form Chỉnh Sửa - GET /students/{id}/edit
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = iStudent.getStudentById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        model.addAttribute("isEdit", true);
        return "student-form";
    }

    // 6. Xử lý Chỉnh Sửa - POST /students/{id}
    @PostMapping("/{id}")
    public String updateStudent(@PathVariable String id, @ModelAttribute Student student) {
        iStudent.updateStudent(id, student);
        return "redirect:/students";
    }

    // 7. Xử lý Xóa - POST /students/{id}/delete
    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable String id) {
        iStudent.deleteStudent(id);
        return "redirect:/students";
    }
}
