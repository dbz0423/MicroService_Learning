package top.zhu.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhu.userservice.model.entity.User;
import top.zhu.userservice.model.vo.UserVo;

public interface UserService extends IService<User> {

    UserVo userInfo(Integer id);

    void addBonus(Integer id , Integer bonus);
}
