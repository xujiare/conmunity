package com.sb.stu.spstu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.sb.stu.spstu.mapper"})
public class SpstuApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpstuApplication.class, args);
    }

}
