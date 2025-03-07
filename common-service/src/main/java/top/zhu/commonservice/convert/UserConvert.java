package top.zhu.commonservice.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.zhu.commonservice.model.entity.User;
import top.zhu.commonservice.model.dto.UserDTO;

@Mapper
public interface UserConvert {

    // 获取 UserConvert 实例，由 MapStruct 自动生成实现类并提供实例
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    // 将 User 对象转换为 UserInfoVO对象
    UserDTO convert(User user);

}
