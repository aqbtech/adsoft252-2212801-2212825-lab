package vn.edu.hcmut.cse.adsoftweng.lab.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.*;

import java.util.List;
@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {
    @Id
    private String id; // Sử dụng String (ví dụ MSSV hoặc UUID) // Không dùng @GeneratedValue
    private String name;
    private String email;
    private int age;
}