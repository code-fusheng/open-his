package xyz.fusheng;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @FileName: xyz.fusheng.SystemApplication
 * @Author: code-fusheng
 * @Date: 2020/8/31 14:53
 * @version: 1.0
 * Description: 系统启动类
 */

@SpringBootApplication
@MapperScan("xyz.fusheng.mapper")
@EnableDubbo
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
        System.out.println("主系统启动成功！");
    }
}
