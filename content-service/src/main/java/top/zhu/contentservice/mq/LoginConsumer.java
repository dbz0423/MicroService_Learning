package top.zhu.contentservice.mq;

import lombok.AllArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;
import top.zhu.contentservice.mapper.BonusLogMapper;
import top.zhu.contentservice.model.entity.BonusLog;
import top.zhu.contentservice.openfeign.UserService;

@Service
@RocketMQMessageListener(topic = "LoginTopic_zhuhaoran", consumerGroup = "zhuhaoran-c-group")
@AllArgsConstructor
public class LoginConsumer implements RocketMQListener<String> {
    private final UserService userService;
    private final BonusLogMapper bonusLogMapper;

    @Override
    public void onMessage(String s) {
        String[] split = s.split(":");
        Integer id = Integer.valueOf(split[0]);
        Integer bonus = Integer.valueOf(split[1]);

        userService.addBonus(id, bonus);
        BonusLog bonusLog = new BonusLog();
        bonusLog.setUserId(id);
        bonusLog.setBonus(bonus);
        bonusLog.setEvent("每日签到加积分");
        bonusLogMapper.insert(bonusLog);
    }
}
