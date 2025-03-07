package top.zhu.commonservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "deepseek.api")
@Data
public class DeepSeekConfig {
    private String key;
    private String url;
    private int timeout;
}