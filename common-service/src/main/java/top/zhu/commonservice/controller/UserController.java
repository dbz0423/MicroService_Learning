package top.zhu.commonservice.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.zhu.commonservice.common.result.Result;
import top.zhu.commonservice.config.NacosConfig;
import top.zhu.commonservice.model.dto.UserDTO;
import top.zhu.commonservice.service.UserService;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @Resource
    private NacosConfig nacosConfig;

    @GetMapping("/user/{id}")
    public Result<UserDTO> userInfo(@PathVariable Integer id) {
        Result result = new Result();
        if (nacosConfig.getServerFlag()) {
            if (userService.userInfo(id) != null) {
                result.setCode(200);
                result.setMsg("success");
                result.setData(userService.userInfo(id));
                return result;
            }else {
                result.setCode(404);
                result.setMsg("用户不存在");
                result.setData(null);
                return result;
            }

        }else {
            result.setCode(503);
            result.setMsg("用户服务正在维护中，请稍后。。。");
            result.setData(null);
            return result;
        }
    }

}
