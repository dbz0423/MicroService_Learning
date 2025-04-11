package top.zhu.contentservice.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RocketMQMessageListener(topic = "zhuhaoran-topic" , consumerGroup = "zhuhaoran-c-group")
public class RocketmqConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        log.info("收到消息：{}",s);
    }
}
