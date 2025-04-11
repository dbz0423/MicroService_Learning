package top.zhu.userservice.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import top.zhu.userservice.comon.Result;
import top.zhu.userservice.config.NacosConfig;
import top.zhu.userservice.service.UserService;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @Resource
    private NacosConfig nacosConfig;

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
    public Result userInfo(@PathVariable Integer id) {
        Result result = new Result();
        if (nacosConfig.getServerFlag()) {
            if (userService.userInfo(id) != null) {
                result.setCode(200);
                result.setMsg("success");
                result.setData(userService.userInfo(id));
                return result;
            } else {
                result.setCode(404);
                result.setMsg("用户不存在");
                result.setData(null);
                return result;
            }

        } else {
            result.setCode(503);
            result.setMsg("用户服务正在维护中，请稍后。。。");
            result.setData(null);
            return result;
        }
    }

    @PostMapping("/user/addBonus")
    public Result<String> addBonus(@RequestParam Integer id, @RequestParam Integer bonus) {
        userService.addBonus(id, bonus);
        return Result.ok("积分增加成功");
    }


}
