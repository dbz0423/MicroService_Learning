package top.zhu.requestservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController2 {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/restTemplateTest")
    public String restTemplateTest() {
        String result = restTemplate.getForObject("http://localhost:8080/hello", String.class);
        return result;
    }

    @GetMapping("/restTemplateTest1")
    public String restTemplateTest1() {
        String result = restTemplate.getForObject("https://www.wanandroid.com/navi/json", String.class);
        return result;
    }
}
