package top.zhu.adminservice.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomMetricConfig {

    @Resource
    private MeterRegistry meterRegistry;

    @Bean
    public Counter customCounter() {
        // 创建一个自定义计数器
        return Counter.builder("custom.counter")
                .description("自定义计数器")
                .register(meterRegistry);
    }

    @Bean
    public Timer customTimer() {
        // 创建一个自定义计时器
        return Timer.builder("custom.timer")
                .description("自定义计时器")
                .register(meterRegistry);
    }
}
