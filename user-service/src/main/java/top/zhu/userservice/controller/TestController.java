package top.zhu.userservice.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhu.userservice.config.NacosConfig;

@RestController
// 实现nacos配置不重启就能刷新数据(第一种方式)
//@RefreshScope
public class TestController {

    @Resource
    private NacosConfig nacosConfig;

    @GetMapping("/test")
    public String test() {
        return "读取到配置中心的值：" + nacosConfig.getName() + " " + nacosConfig.getPhone();
    }

}
