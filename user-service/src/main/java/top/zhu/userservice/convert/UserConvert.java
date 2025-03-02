package top.zhu.userservice.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.zhu.userservice.model.entity.User;
import top.zhu.userservice.model.vo.UserVo;

@Mapper
public interface UserConvert {

    // 获取 UserConvert 实例，由 MapStruct 自动生成实现类并提供实例
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    // 将 User 对象转换为 UserInfoVO对象
    UserVo convert(User user);

}
