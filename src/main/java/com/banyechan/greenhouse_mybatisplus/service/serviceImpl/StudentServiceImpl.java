package com.banyechan.greenhouse_mybatisplus.service.serviceImpl;


import com.banyechan.greenhouse_mybatisplus.entity.Student;
import com.banyechan.greenhouse_mybatisplus.mapper.StudentMapper;
import com.banyechan.greenhouse_mybatisplus.service.StudentService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.jdbc.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student getByByPrimaryKey(Integer id) {

        return studentMapper.selectById(id);
    }

    @Override
    public List<Student> listStudent(Student record) {
        QueryWrapper<Student> wrapper = new QueryWrapper<Student>(record);
        wrapper = Wrappers.query(record);

        /**  下面三种多条件查询方式 都是
         *          按所传字段中非null 的属性进行多条件搜索
         */
        //return studentMapper.selectList(wrapper);  //
        //return this.baseMapper.selectList(new QueryWrapper<>(record)); //按所传字段中非null 的属性进行多条件搜索
        //return super.list(new QueryWrapper<>(record));//按所传字段中非null 的属性进行多条件搜索

        /**   下面这个多条件查询
         * 所有所传字段中非null的属性，都会拼接到where 后面
         *   lambda() ： 相当于 and连接符
         *
         *     {
         *     "name":"任",
         *     "age":null,
         *     "sex":"girl"
         *     }
         *    super.list(new QueryWrapper<>(record).lambda().like(!StringUtils.isNullOrEmpty(record.getName),Student::getName, record.getName));
         *
         *    打印出的sql :   SELECT id,name,age,sex FROM student WHERE name = ? and sex=? AND (name LIKE ?)
         *
         *    但是如果修改成了  String name = record.getName();
         *                    record.setName(null);
         *                return super.list(new QueryWrapper<>(record).lambda().like(!StringUtils.isNullOrEmpty(name),Student::getName, name));
         *
         *    打印出的sql :   SELECT id,name,age,sex FROM student WHERE sex=? AND (name LIKE ?)
         *
         */
        String name = record.getName();
        record.setName(null);
        return super.list(new QueryWrapper<>(record).lambda().like(!StringUtils.isNullOrEmpty(name),Student::getName, name));
    }



    @Override
    public List<Student> listAllStudent() {
        return studentMapper.selectByMap(null);
    }

    @Override
    public Page page(Page<Student> page, Student student) {
        //Page temPage = new Page(3,2);
        log.info("------------- size={},current={}",page.getSize(),page.getCurrent());

        Page<Student> result = super.baseMapper.selectPage(page, new QueryWrapper<Student>(student));


        return result;
    }

    @Override
    public IPage<Student> ipage(IPage<Student> page, Student student) {

        this.page((Page<Student>) page,student);
        this.page(page,Wrappers.query(student).lambda().like(Student::getName,student.getName()));

        return  super.page(page,Wrappers.query(student));
       // return  this.baseMapper.selectPage(page,new QueryWrapper<Student>(student));
    }



}
