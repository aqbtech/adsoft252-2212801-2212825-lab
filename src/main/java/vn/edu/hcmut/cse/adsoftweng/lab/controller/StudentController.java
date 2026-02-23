package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.service.IStudent;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private IStudent iStudent;

    @GetMapping
    public String getAllStudents(
            @RequestParam(required = false) String keyword,
            Model model) {
        List<Student> students;
        if (keyword != null && !keyword.isEmpty()) {
            students = iStudent.searchByName(keyword);
        } else {
            students = iStudent.getAllStudents();
        }
        model.addAttribute("dsSinhVien", students);
        model.addAttribute("keyword", keyword != null ? keyword : "");
        return "students";
    }

    @GetMapping("/{id}")
    public String getStudentById(@PathVariable String id, Model model) {
        Student student = iStudent.getStudentById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "student-detail";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("isEdit", false);
        return "student-form";
    }

    @PostMapping
    public String createStudent(@ModelAttribute Student student,
            RedirectAttributes redirectAttributes) {
        iStudent.save(student);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm sinh viên thành công!");
        return "redirect:/students";
    }

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

    @PutMapping("/{id}/edit")
    public String updateStudent(@PathVariable String id,
            @ModelAttribute Student student,
            RedirectAttributes redirectAttributes) {
        iStudent.update(id, student);
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật sinh viên thành công!");
        return "redirect:/students/" + id;
    }

    @DeleteMapping("/{id}/delete")
    public String deleteStudent(@PathVariable String id,
            RedirectAttributes redirectAttributes) {
        iStudent.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa sinh viên thành công!");
        return "redirect:/students";
    }
}
