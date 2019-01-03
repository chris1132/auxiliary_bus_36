package com.auxiliarybus.service.impl;


import com.auxiliarybus.entity.Student;
import com.auxiliarybus.mapper.StudentMapper;
import com.auxiliarybus.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wangchaohui on 2018/1/18.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired(required = false)
    private StudentMapper studentMapper;


    public Student getStudentById(int id) {
        return studentMapper.getStudentById(id);
    }

    public void insert(Student student) {
        studentMapper.insert(student);
    }

    public List<Student> getList() {
        return studentMapper.getList();
    }

}
