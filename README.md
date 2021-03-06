# 教学管理平台xxedu（教师端）
![页面展示截图：教师用户首页](https://github.com/hdyhhjya/xxedu/blob/master/example.png)
## 业务需求
- 管理员
  - 登录
  - 维护账户信息（包括查看所有普通用户、新增用户、重置用户密码）
  - 维护学校信息（包括查看所有学校、新增学校、修改学校信息）
- 教师（普通用户）
  - 登录
  - 修改密码
  - 维护班级（包括查看任教班级、新增班级、删除无效班级）
  - 维护学期（包括增删改查）
  - 维护个人课表（包括增删改查）
  - 维护学生信息（包括增删改查）
  - 维护学生成绩（包括查看学生本学期已有成绩、新增学生成绩、修改学生成绩）
## 数据库设计
  - class (_id_, grade, number, school_id)
  - course (_id_, full_score, excellent_score, passing_score, teacher_id, subject_id, semester_id, class_id)
  - course_time (_id_, weekday, number, course_id)
  - school (_id_, name, address)
  - score (_student_id, course_id_, score)
  - semester (_id_, name, start_date, end_date, school_id)
  - student (_id_, name, gender, birthday, class_id)
  - subject (_id_, name)
  - teacher (_id_, name, email, school_id)
  - user (_id_, username, password, teacher_id)
## 开发环境
- Windows 10
- IntelliJ IDEA 2020.1.3
- Java 11
- Spring Boot 2.3.2
- MySQL 8.0
## Maven依赖
- Spring Web
- Spring Boot DevTools
- Spring Security
- Lombok
- Mybatis Framework
- Mysql Driver
- Druid
- PageHelper
- Thymeleaf
