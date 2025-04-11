package top.zhu.contentservice.mq;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginProducer {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(Integer id, Integer bonus) {
        rocketMQTemplate.convertAndSend("LoginTopic_zhuhaoran", id + ":" + bonus);
    }
}
