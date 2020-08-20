# 教学管理平台xxedu（教师端）
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
- 初始版本  
![image](https://github.com/hdyhhjya/images/blob/master/school_db.png)
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
- PageHelper
- Thymeleaf
