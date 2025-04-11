package top.zhu.clientservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class ClientServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(ClientServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void task() {
        logger.info("正在执行计划任务...");
    }
}
