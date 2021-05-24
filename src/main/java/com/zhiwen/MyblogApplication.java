package com.zhiwen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 启动器
 *

 * @date : 2020/1/7 08:41
 */
@MapperScan("com.zhiwen.mapper")
@SpringBootApplication
public class MyblogApplication {
    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(MyblogApplication.class, args);
    }

}
