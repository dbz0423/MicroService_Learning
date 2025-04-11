package top.zhu.contentservice.mq;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RocketmqProducer {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(String topic, String msg) {
        rocketMQTemplate.convertAndSend(topic, msg);
        log.info("消息已发送：", msg);
    }
}
