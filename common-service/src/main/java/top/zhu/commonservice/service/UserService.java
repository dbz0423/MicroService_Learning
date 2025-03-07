package top.zhu.commonservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhu.commonservice.model.entity.User;
import top.zhu.commonservice.model.dto.UserDTO;

public interface UserService extends IService<User> {

    UserDTO userInfo(Integer id);
}
