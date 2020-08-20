package com.daisy.xxedu.mapper;

import com.daisy.xxedu.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int insert(User user);
    User findByUsername(String username);
    List<User> getTeacherUsers();
    int updatePassword(@Param("id") Long id, @Param("password") String password);
    int deleteById(@Param("id")Long id);
    Long getTeacherIdByUserId(Long id);
}
