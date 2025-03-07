package top.zhu.userservice;

import com.alibaba.spring.beans.factory.annotation.EnableConfigurationBeanBinding;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import top.zhu.userservice.config.NacosConfig;

@SpringBootApplication
@MapperScan(basePackages = {"top.zhu.userservice.mapper"})
@EnableConfigurationProperties({NacosConfig.class})
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
