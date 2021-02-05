package com.banyechan.greenhouse_mybatisplus.service;



import com.banyechan.greenhouse_mybatisplus.entity.Student;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface StudentService extends IService<Student> {

    Student getByByPrimaryKey(Integer id);

    List<Student> listStudent(Student record);
//
//    boolean  saveStudent(StudentModel record);
//
//    boolean  updateStudent(StudentModel record);
//
//    boolean  deleteStudent(Integer id);
//
      List<Student> listAllStudent();
//
//    List<Map<String,String>> getCountBySex();
//
//    Object getById(Integer id);

    Page page(Page<Student> page, Student student);


    IPage<Student> ipage(IPage<Student> page, Student student);

}
