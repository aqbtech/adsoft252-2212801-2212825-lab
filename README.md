# Student Management — Ứng dụng Quản lý Sinh viên

Ứng dụng web quản lý sinh viên được xây dựng bằng **Spring Boot**, **Thymeleaf**, và **PostgreSQL**.

## Thành viên nhóm


| MSSV    | Họ và Tên              |
| ------- | ---------------------- |
| 2212825 | Từ Văn Nguyễn Anh Quân |
| 2212801 | Nguyễn Minh Quân       |

## Public URL của Web Service
https://adsoft252-2212801-2212825-lab.onrender.com/


## Cách chạy dự án (Local)

### Yêu cầu

- Java 17+
- Maven 3.9+
- PostgreSQL

### Bước 1: Cấu hình database

Tạo file `.env` tại thư mục gốc:

```env
DATABASE_URL=jdbc:postgresql://localhost:5432/student_management
DB_USERNAME=postgres
DB_PASSWORD=your_password_here
```

### Bước 2: Export các biến môi trường và chạy ứng dụng

```bash
set -a && source .env && set +a && ./mvnw spring-boot:run
```

Truy cập: [http://localhost:8080/students](http://localhost:8080/students)

## Trả lời cho các câu hỏi lý thuyết trong phần Lab

## Screenshot cho các module trong Lab 4

## Công nghệ sử dụng

- **Backend:** Spring Boot 4.0.2, Spring Data JPA
- **Frontend:** Thymeleaf Template Engine
- **Database:** PostgreSQL (Neon.tech serverless)
- **Build:** Maven, Docker (multi-stage)
- **Deploy:** Render.com (Web Service)

## Chức năng


| Chức năng    | URL                          | Mô tả                            |
| ------------ | ---------------------------- | -------------------------------- |
| Danh sách SV | `GET /students`              | Hiển thị bảng, tìm kiếm theo tên |
| Chi tiết SV  | `GET /students/{id}`         | Xem thông tin chi tiết           |
| Thêm mới     | `GET /students/new`          | Form thêm sinh viên              |
| Lưu mới      | `POST /students`             | Xử lý thêm sinh viên             |
| Chỉnh sửa    | `GET /students/{id}/edit`    | Form sửa (điền sẵn data)         |
| Cập nhật     | `POST /students/{id}`        | Xử lý cập nhật                   |
| Xóa          | `POST /students/{id}/delete` | Xóa với xác nhận                 |

## Cấu trúc project

```
src/main/java/vn/edu/hcmut/cse/adsoftweng/lab/
├── controller/
│   └── StudentController.java      # MVC Controller
├── entity/
│   └── Student.java                # JPA Entity
├── repository/
│   └── StudentRepository.java      # Spring Data JPA Repository
├── service/
│   ├── IStudent.java               # Service Interface
│   └── StudentImpl.java            # Service Implementation
└── StudentManagementApplication.java

src/main/resources/
├── templates/
│   ├── students.html               # Trang danh sách
│   ├── student-detail.html         # Trang chi tiết
│   └── student-form.html           # Form thêm/sửa
└── application.properties
```

## Triển khai (Deployment)

### Docker

Build thử docker ở local.

```bash
docker build -t student-management:latest .
docker run -p 8080:8080 \
  -e DATABASE_URL=jdbc:postgresql://host/db \
  -e DB_USERNAME=user \
  -e DB_PASSWORD=pass \
  student-management:latest
```

### Render.com + Neon.tech

1. Tạo database trên [Neon.tech](https://neon.com)
2. Tạo Web Service trên [Render.com](https://render.com) -> chọn Docker runtime
3. Cấu hình Environment Variables:
   - `DATABASE_URL`: Connection string từ Neon (thêm prefix `jdbc:`)
   - `DB_USERNAME`: Username
   - `DB_PASSWORD`: Password
4. Deploy — Render auto-deploy khi push lên `main`
