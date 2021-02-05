package com.banyechan.greenhouse_mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("student")  //该注解在实体类上指定映射数据库表名 数据库名与实体类名一样时可以不写
public class Student {


    private Integer id;

    private String name;

    private Integer age;

    private String sex;

}