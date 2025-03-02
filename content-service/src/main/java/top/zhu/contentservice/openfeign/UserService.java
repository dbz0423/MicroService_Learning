package top.zhu.contentservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.zhu.contentservice.model.vo.PUserVo;

//@FeignClient(value = "user-service")
@FeignClient(value = "python-service")
public interface UserService {
    @GetMapping("/users/<int:user_id>")
    PUserVo getUserInfo(int id);
}
