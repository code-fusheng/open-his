package xyz.fusheng;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @FileName: StatisticsApplication
 * @Author: code-fusheng
 * @Date: 2020/8/31 15:55
 * @version: 1.0
 * Description: 统计分析服务启动类
 */

@SpringBootApplication
@MapperScan(basePackages = {"xyz.fusheng.mapper"})
@EnableDubbo
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class,args);
        System.out.println("statistics 统计分析子系统启动成功");
    }
}
