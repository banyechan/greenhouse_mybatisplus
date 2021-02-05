package com.banyechan.greenhouse_mybatisplus.controller;


import com.banyechan.greenhouse_mybatisplus.entity.Student;
import com.banyechan.greenhouse_mybatisplus.service.StudentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping("/page")
    public Page<Student> getStudentPage(Page<Student> page , @RequestBody Student record) {
        log.info("----  调用列表查询接口 -----");
        Page<Student> result = studentService.page(page,record);
        return result;
    }

    @PostMapping("/ipage")
    public IPage<Student> getStudentIPage(Page<Student> page , @RequestBody Student record) {
        log.info("----  调用列表查询接口 -----");
        IPage<Student> resultList = studentService.ipage(page,record);
        return resultList;
    }

    @PostMapping("/list")
    public List<Student> getStudentList(@RequestBody Student record) {
        log.info("----  调用列表查询接口 -----");
        List<Student> resultList = studentService.listStudent(record);
        return resultList;
    }







    @GetMapping("/get")
    public Student getStudent(@RequestParam("id") Integer id) {
        log.info("----  调用单个查询接口,id:{} -----",id);
        Student result = studentService.getByByPrimaryKey(id);
        return result;
    }

//    @PostMapping("/add")
//    public boolean addStudent(@RequestBody StudentModel record) {
//        log.info("----  调用新增接口 -----");
//        return studentService.saveStudent(record);
//    }
//
//    @PostMapping("/update")
//    public boolean updateStudent(@RequestBody StudentModel record) {
//        log.info("----  调用修改接口 -----");
//        return studentService.updateStudent(record);
//    }
//
//    @DeleteMapping("/del")
//    public boolean deleteStudent(Integer id) {
//        log.info("----  调用删除接口,id:{} -----",id);
//        return studentService.deleteStudent(id);
//    }
//
//
//    @GetMapping("/getMap")
//    public Map<String,String> getCountBySex() {
//        log.info("----  调用getCountBySex -----");
//        Map<String,String> map = new HashMap<>();
//        List<Map<String,String>> mapList = studentService.getCountBySex();
//        if(mapList != null && mapList.size() > 0){
//            for(Map tem : mapList){
//                log.info("key = {},value = {}",tem.get("type"),tem.get("num"));
//                map.put(tem.get("type").toString(),tem.get("num").toString());
//            }
//        }
//
//        return map;
//    }
//
//
//
//    @GetMapping("/getObject")
//    public Object getStudent2(@RequestParam("id") Integer id) {
//        log.info("----  调用单个查询接口,id:{} -----",id);
//        Object result = studentService.getById(id);
//        return result;
//    }
}
