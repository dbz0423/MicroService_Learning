package top.zhu.adminservice.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
public class CustomMetricsController {

    @Resource
    private Counter customCounter;

    @Resource
    private Timer customTimer;

    @GetMapping("/increment")
    public String incrementCounter() {
        // 增加计数器的值
        customCounter.increment();
        return "计数器已增加!";
    }

    @GetMapping("/time")
    public String recordTime() {
        // 记录接口的执行时间
        customTimer.record(() -> {
            try {
                // 模拟接口执行时间
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        return "定时器已记录!";
    }

}
