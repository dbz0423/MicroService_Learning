package top.zhu.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.zhu.userservice.convert.UserConvert;
import top.zhu.userservice.mapper.UserMapper;
import top.zhu.userservice.model.entity.User;
import top.zhu.userservice.model.vo.UserVo;
import top.zhu.userservice.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper , User> implements UserService {
    private final UserMapper userMapper;

    @Override
    public UserVo userInfo(Integer id) {
        User user = baseMapper.selectById(id);
        UserVo userVo = UserConvert.INSTANCE.convert(user);
        return userVo;
    }

    @Override
    public void addBonus(Integer id, Integer bonus) {
        User user = baseMapper.selectById(id);
        if (user != null) {
            user.setBonus(user.getBonus() + bonus);
            userMapper.updateById(user);
        }
    }
}
