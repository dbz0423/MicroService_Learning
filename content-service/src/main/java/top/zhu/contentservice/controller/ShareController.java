package top.zhu.contentservice.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zhu.contentservice.comon.Result;
import top.zhu.contentservice.model.vo.PUserVo;
import top.zhu.contentservice.openfeign.UserService;
import top.zhu.contentservice.service.ShareService;

import javax.swing.plaf.TableHeaderUI;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class ShareController {
    private final ShareService shareService;
    @Resource
    private UserService userService;

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
}
