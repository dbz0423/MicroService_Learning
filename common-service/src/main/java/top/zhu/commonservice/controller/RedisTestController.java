package top.zhu.commonservice.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import top.zhu.commonservice.common.result.Result;
import top.zhu.commonservice.model.dto.RedisDTO;

@RestController("/redis")
public class RedisTestController {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisTestController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/{key}")
    public Result<String> setValue(
            @PathVariable String key,
            @RequestBody RedisDTO value) {
        redisTemplate.opsForValue().set(key, value.getValue());
        return Result.ok("数据保存到 Redis 成功");
    }

    /**
     * 从 Redis 获取数据
     *
     * @param key 要获取的键
     */
    @GetMapping("/{key}")
    public Result<?> getValue(@PathVariable String key) {
        Object value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return Result.error(HttpStatus.NOT_FOUND.value(), "没有找到" + key);
        }
        return Result.ok(value);
    }

}
