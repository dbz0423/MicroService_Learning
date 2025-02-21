package top.zhu.userservivce.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/user")
    public String user(@RequestParam String username) {
        return "User:" + username;
    }

    @GetMapping("/user/question")
    public String addQuestion(@RequestParam String question) {
        String questionServiceUrl = "http://localhost:8084/question?question=" + question;
        return restTemplate.getForObject(questionServiceUrl, String.class);
    }

}
