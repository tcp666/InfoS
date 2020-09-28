package com.keshe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/5/26 21:46
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.keshe.mapper"})
public class InfoStackMain {
    public static void main(String[] args) {
        SpringApplication.run(InfoStackMain.class,args);
    }
}
