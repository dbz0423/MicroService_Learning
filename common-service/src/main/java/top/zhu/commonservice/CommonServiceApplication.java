package top.zhu.commonservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import top.zhu.commonservice.config.NacosConfig;
import top.zhu.commonservice.config.OssClientConfig;

@SpringBootApplication
@MapperScan(basePackages = {"top.zhu.commonservice.mapper"})
@EnableConfigurationProperties({NacosConfig.class , OssClientConfig.class})
public class CommonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonServiceApplication.class, args);
    }

}
