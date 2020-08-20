package com.daisy.xxedu.service.impl;

import com.daisy.xxedu.entity.School;
import com.daisy.xxedu.mapper.SchoolMapper;
import com.daisy.xxedu.service.SchoolService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public Map<String, Object> getSchools(int pageNum) {
        int pageSize = pageNum==0 ? 0 : 10;
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum, pageSize);
        List<School> schoolList = schoolMapper.getSchools();
        map.put("schools", schoolList);
        map.put("pageInfo", new PageInfo(schoolList));
        return map;
    }

    @Override
    public int updateSchool(School school) {
        return schoolMapper.updateSchool(school);
    }

    @Override
    public School getSchoolById(Long id) {
        return schoolMapper.getSchoolById(id);
    }

    @Override
    public int insertSchool(School school) {
        return schoolMapper.insert(school);
    }
}
