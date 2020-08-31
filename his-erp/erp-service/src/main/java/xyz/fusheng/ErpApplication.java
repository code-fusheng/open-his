package xyz.fusheng;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @FileName: xyz.fusheng.ErpApplication
 * @Author: code-fusheng
 * @Date: 2020/8/31 15:19
 * @version: 1.0
 * Description: Erp 服务启动类
 */

@SpringBootApplication
@MapperScan(basePackages = {"xyz.fusheng.mapper"})
@EnableDubbo
public class ErpApplication {
    public static void main(String[] args) {
        SpringApplication.run(ErpApplication.class,args);
        System.out.println("ERP子系统启动成功！");
    }
}
