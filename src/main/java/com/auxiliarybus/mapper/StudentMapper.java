package com.auxiliarybus.mapper;

import com.auxiliarybus.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by wangchaohui on 2018/1/18.
 */
//@CacheConfig(cacheNames="student")
@Repository
public interface StudentMapper {

    //    @Cacheable(key="#p0")
    Student getStudentById(int id);

    int insert(Student student);

    List<Student> getList();

}
