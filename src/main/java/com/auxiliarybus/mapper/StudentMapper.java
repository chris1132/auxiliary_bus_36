package com.auxiliarybus.mapper;

import com.auxiliarybus.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by wangchaohui on 2018/1/18.
 */
@Repository
public interface StudentMapper {

    Student getStudentById(int id);

    int insert(Student student);

    List<Student> getList();

}
