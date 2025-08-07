--- Tạo các bảng trước khi insert dữ liệu

-- Tạo bảng roles
CREATE TABLE IF NOT EXISTS roles (
    id BIGINT PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL
);

-- Tạo bảng user_profiles
CREATE TABLE IF NOT EXISTS user_profiles (
    id BIGINT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    address VARCHAR(255)
);

-- Tạo bảng users
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    profile_id BIGINT,
    FOREIGN KEY (profile_id) REFERENCES user_profiles(id)
);

-- Tạo bảng user_roles (many-to-many)
CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Tạo bảng departments
CREATE TABLE IF NOT EXISTS departments (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Tạo bảng courses
CREATE TABLE IF NOT EXISTS courses (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Tạo bảng students
CREATE TABLE IF NOT EXISTS students (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    address VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    department_id BIGINT,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

-- Tạo bảng student_courses (many-to-many)
CREATE TABLE IF NOT EXISTS student_courses (
    student_id BIGINT,
    course_id BIGINT,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);
-- Insert Roles
INSERT IGNORE INTO roles (id, code, name) VALUES 
(1, 'ADMIN', 'Administrators'),
(2, 'USER', 'Users'),
(3, 'STUDENT', 'Students'),
(4, 'TEACHER', 'Teachers');

-- Insert User Profiles
INSERT IGNORE INTO user_profiles (id, full_name, email, address) VALUES 
(1, 'Nguyễn Văn Admin', 'admin@ktc.edu.vn', 'Hà Nội'),
(2, 'Trần Thị User', 'user@ktc.edu.vn', 'Hồ Chí Minh'),
(3, 'Lê Văn Teacher', 'teacher@ktc.edu.vn', 'Đà Nẵng'),
(4, 'Phạm Thị Student', 'student@ktc.edu.vn', 'Cần Thơ');

-- Insert Users
INSERT IGNORE INTO users (id, username, password, profile_id) VALUES 
(1, 'admin', 'admin123', 1),
(2, 'user', 'user123', 2),
(3, 'teacher', 'teacher123', 3),
(4, 'student', 'student123', 4);

-- Insert User Roles mapping
INSERT IGNORE INTO user_roles (user_id, role_id) VALUES 
(1, 1), -- admin có role ADMIN
(1, 2), -- admin cũng có role USER
(2, 2), -- user có role USER
(3, 4), -- teacher có role TEACHER
(3, 2), -- teacher cũng có role USER
(4, 3), -- student có role STUDENT
(4, 2); -- student cũng có role USER

-- Insert Departments
INSERT IGNORE INTO departments (id, name) VALUES 
(1, 'Công nghệ Thông tin'),
(2, 'Quản trị Kinh doanh'),
(3, 'Kế toán'),
(4, 'Marketing'),
(5, 'Ngôn ngữ Anh');

-- Insert Courses
INSERT IGNORE INTO courses (id, name) VALUES 
(1, 'Lập trình Java'),
(2, 'Cơ sở dữ liệu'),
(3, 'Phát triển Web'),
(4, 'Quản trị học'),
(5, 'Marketing căn bản'),
(6, 'Kế toán tài chính'),
(7, 'Tiếng Anh giao tiếp'),
(8, 'Tin học văn phòng');

-- Insert Students
INSERT IGNORE INTO students (id, name, email, address, password, department_id) VALUES 
(1, 'Nguyễn Văn An', 'an.nguyen@student.ktc.edu.vn', 'Quận 1, TP.HCM', 'password123', 1),
(2, 'Trần Thị Bình', 'binh.tran@student.ktc.edu.vn', 'Quận 2, TP.HCM', 'password123', 1),
(3, 'Lê Văn Cường', 'cuong.le@student.ktc.edu.vn', 'Quận 3, TP.HCM', 'password123', 2),
(4, 'Phạm Thị Dung', 'dung.pham@student.ktc.edu.vn', 'Quận 4, TP.HCM', 'password123', 2),
(5, 'Hoàng Văn Em', 'em.hoang@student.ktc.edu.vn', 'Quận 5, TP.HCM', 'password123', 3),
(6, 'Ngô Thị Phương', 'phuong.ngo@student.ktc.edu.vn', 'Quận 6, TP.HCM', 'password123', 3),
(7, 'Vũ Văn Giang', 'giang.vu@student.ktc.edu.vn', 'Quận 7, TP.HCM', 'password123', 4),
(8, 'Đỗ Thị Hạnh', 'hanh.do@student.ktc.edu.vn', 'Quận 8, TP.HCM', 'password123', 4),
(9, 'Bùi Văn Ích', 'ich.bui@student.ktc.edu.vn', 'Quận 9, TP.HCM', 'password123', 5),
(10, 'Lý Thị Kim', 'kim.ly@student.ktc.edu.vn', 'Quận 10, TP.HCM', 'password123', 5);

-- Insert Student-Course mapping
INSERT IGNORE INTO student_courses (student_id, course_id) VALUES 
-- Sinh viên IT
(1, 1), (1, 2), (1, 3), (1, 8), -- An học Java, Database, Web, Tin học văn phòng
(2, 1), (2, 2), (2, 7), (2, 8), -- Bình học Java, Database, Tiếng Anh, Tin học văn phòng

-- Sinh viên Quản trị
(3, 4), (3, 5), (3, 7), (3, 8), -- Cường học Quản trị, Marketing, Tiếng Anh, Tin học văn phòng
(4, 4), (4, 6), (4, 7), (4, 8), -- Dung học Quản trị, Kế toán, Tiếng Anh, Tin học văn phòng

-- Sinh viên Kế toán
(5, 6), (5, 7), (5, 8), (5, 4), -- Em học Kế toán, Tiếng Anh, Tin học văn phòng, Quản trị
(6, 6), (6, 8), (6, 7), (6, 5), -- Phương học Kế toán, Tin học văn phòng, Tiếng Anh, Marketing

-- Sinh viên Marketing
(7, 5), (7, 7), (7, 8), (7, 4), -- Giang học Marketing, Tiếng Anh, Tin học văn phòng, Quản trị
(8, 5), (8, 6), (8, 7), (8, 8), -- Hạnh học Marketing, Kế toán, Tiếng Anh, Tin học văn phòng

-- Sinh viên Ngôn ngữ Anh
(9, 7), (9, 8), (9, 4), (9, 5),  -- Ích học Tiếng Anh, Tin học văn phòng, Quản trị, Marketing
(10, 7), (10, 8), (10, 6), (10, 3); -- Kim học Tiếng Anh, Tin học văn phòng, Kế toán, Web