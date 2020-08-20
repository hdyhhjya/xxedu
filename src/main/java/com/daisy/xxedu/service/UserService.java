package com.daisy.xxedu.service;

import com.daisy.xxedu.entity.User;
import com.daisy.xxedu.entity.UserInformationForm;

import java.util.Map;

public interface UserService {
    Map<String, Object> getUsers(int pageNum);
    void register(UserInformationForm form);
    boolean resetPassword(Long id);
    boolean deleteUser(Long id);
}
