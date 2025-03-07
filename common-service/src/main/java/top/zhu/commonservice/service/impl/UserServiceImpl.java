package top.zhu.commonservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.zhu.commonservice.convert.UserConvert;
import top.zhu.commonservice.mapper.UserMapper;
import top.zhu.commonservice.model.entity.User;
import top.zhu.commonservice.model.dto.UserDTO;
import top.zhu.commonservice.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper , User> implements UserService {
    @Override
    public UserDTO userInfo(Integer id) {
        User user = baseMapper.selectById(id);
        UserDTO userDTO = UserConvert.INSTANCE.convert(user);
        return userDTO;
    }
}
