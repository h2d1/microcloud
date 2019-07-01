package cn.gobaby;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.gobaby.mapper")
public class ProductApp{
    public static void main(String[] args) {
        SpringApplication.run(ProductApp.class,args);
    }
}