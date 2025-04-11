package top.zhu.contentservice.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import top.zhu.contentservice.comon.Result;
import top.zhu.contentservice.model.entity.Share;
import top.zhu.contentservice.model.vo.PUserVo;
import top.zhu.contentservice.mq.BonusProducer;
import top.zhu.contentservice.mq.LoginProducer;
import top.zhu.contentservice.mq.RocketmqProducer;
import top.zhu.contentservice.openfeign.UserService;
import top.zhu.contentservice.service.ShareService;

import javax.swing.plaf.TableHeaderUI;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class ShareController {
    private final ShareService shareService;
    @Resource
    private UserService userService;
    @Resource
    private RocketmqProducer rocketmqProducer;
    @Resource
    private BonusProducer bonusProducer;
    @Resource
    private LoginProducer loginProducer;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/share/{id}")
    @SentinelResource(value = "/share/{id}")
    public Map<String, Object> shareInfo(@PathVariable Integer id) {
        // 使用 Map 合并结果
        Map<String, Object> result = new HashMap<>();
        result.put("share", shareService.getShare(id));
        result.put("user", userService.getUserInfo(id).getData());
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return result;
    }

    @GetMapping("/users")
    public Result<PUserVo> getUser(@RequestParam int id) {
        return userService.getUserInfo(id);
    }

    @GetMapping("/mq")
    public Result<String> sendMsg() {
        rocketmqProducer.sendMessage("zhuhaoran-topic", "Hello RocketMQ,这是来自zhu的消息");
        return Result.ok("消息发送成功");
    }

    @PostMapping("/share/publish")
    public Result<String> publish(@RequestBody Share share) {
        shareService.publishShare(share);
        return Result.ok("发布成功，等待审核！");
    }

    @PostMapping("/share/approve/{id}")
    public Result<String> approve(@PathVariable Integer id) {
        shareService.approveShare(id);
        Share share = shareService.getById(id);
        bonusProducer.sendMessage(share.getUserId(), 50);
        return Result.ok("审核通过，积分奖励已发放！");
    }

    @PostMapping("/share/login")
    public Result<String> login(@RequestParam Integer userId) {
        // 生成当前日期的键，格式：sign_in:userId:yyyyMMdd
        String dateKey = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        String redisKey = "sign_in" + userId + ":" + dateKey;

        // 尝试设置键，存在则返回false，不存在则设置并返回true，同时设置1天过期
        Boolean isFirstLogin = redisTemplate.opsForValue().setIfAbsent(redisKey, "1", Duration.ofHours(4));

        if (Boolean.TRUE.equals(isFirstLogin)) {

            loginProducer.sendMessage(userId, 10);
            return Result.ok("签到成功");

        } else {
            return Result.error("今天已签到，请明天再来");
        }
    }
}
