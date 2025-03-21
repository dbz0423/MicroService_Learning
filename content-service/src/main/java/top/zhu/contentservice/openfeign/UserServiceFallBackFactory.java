package top.zhu.contentservice.openfeign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import top.zhu.contentservice.comon.Result;
import top.zhu.contentservice.model.vo.PUserVo;

@Slf4j
@Component
public class UserServiceFallBackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable cause) {
        log.error("用户服务调用异常：", cause);

        return new UserService() {
            @Override
            public Result<PUserVo> getUserInfo(Integer id) {
                PUserVo userVo = new PUserVo();
                userVo.setId(-1);
                userVo.setUsername("异常的用户名");
                userVo.setEmail("dbz2333@163.com");
                return Result.ok(userVo);
            }
        };
    }
}
