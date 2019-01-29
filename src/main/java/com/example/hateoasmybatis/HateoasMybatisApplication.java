package com.example.hateoasmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.PagedResourcesAssembler;

@SpringBootApplication
@MapperScan(basePackages = "com.example.hateoasmybatis")
public class HateoasMybatisApplication {

   public static void main(String[] args) {
        SpringApplication.run(HateoasMybatisApplication.class, args);
    }

}

