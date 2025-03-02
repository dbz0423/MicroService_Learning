package top.zhu.userservice.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.zhu.userservice.model.vo.UserVo;
import top.zhu.userservice.service.UserService;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

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

    @GetMapping("/user/{id}")
    public UserVo userInfo(@PathVariable Integer id) {
        return userService.userInfo(id);
    }

}
