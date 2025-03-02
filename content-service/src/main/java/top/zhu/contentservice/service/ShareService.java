package top.zhu.contentservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhu.contentservice.model.entity.Share;

public interface ShareService extends IService<Share> {
    Share getShare(Integer id);
}
