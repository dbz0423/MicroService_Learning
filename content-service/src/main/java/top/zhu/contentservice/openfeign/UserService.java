package top.zhu.contentservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.zhu.contentservice.comon.Result;
import top.zhu.contentservice.model.vo.PUserVo;

@FeignClient(value = "user-service", fallbackFactory = UserServiceFallBackFactory.class)
//@FeignClient(value = "python-service")
public interface UserService {
    @GetMapping("/user/{id}")
    Result<PUserVo> getUserInfo(@PathVariable Integer id);

    @PostMapping("/user/addBonus")
    Result<String> addBonus(@RequestParam Integer id, @RequestParam Integer bonus);

}
