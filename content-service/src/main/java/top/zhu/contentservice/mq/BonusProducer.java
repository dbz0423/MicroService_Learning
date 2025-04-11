package top.zhu.contentservice.mq;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BonusProducer {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(Integer id, Integer bonus) {
        rocketMQTemplate.convertAndSend("BonusTopic_zhuhaoran", id + ":" + bonus);
    }
}
