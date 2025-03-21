package top.zhu.contentservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import top.zhu.contentservice.config.SentinelConfig;
import top.zhu.contentservice.handler.SentinelExceptionHandler;

@SpringBootApplication
@MapperScan(basePackages = {"top.zhu.contentservice.mapper"})
@EnableFeignClients(basePackages = {"top.zhu.contentservice.openfeign"})
@Import({SentinelConfig.class})
public class ContentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentServiceApplication.class, args);
    }

}
