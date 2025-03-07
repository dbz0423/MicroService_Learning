package top.zhu.commonservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "server")
@Data
public class NacosConfig {
    private String name;
    private String phone;
    private Boolean serverFlag;
}
