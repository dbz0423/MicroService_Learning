package top.zhu.commonservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

// 新增配置类 RestTemplateConfig.java
@Configuration
public class RestTemplateConfig {

    private final DeepSeekConfig deepSeekConfig;

    public RestTemplateConfig(DeepSeekConfig deepSeekConfig) {
        this.deepSeekConfig = deepSeekConfig;
    }

    @Bean
    public RestTemplate deepSeekRestTemplate() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(deepSeekConfig.getTimeout()))
                .setReadTimeout(Duration.ofMillis(deepSeekConfig.getTimeout()))
                .build();
    }
}