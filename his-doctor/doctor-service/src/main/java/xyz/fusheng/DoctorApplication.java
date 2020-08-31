package xyz.fusheng;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @FileName: DoctorApplication
 * @Author: code-fusheng
 * @Date: 2020/8/31 15:38
 * @version: 1.0
 * Description: Doctor 就诊服务启动类
 */

@SpringBootApplication
@MapperScan("xyz.fusheng.mapper")
@EnableDubbo
public class DoctorApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoctorApplication.class, args);
        System.out.println("Doctor就诊子系统启动成功！");
    }
}
