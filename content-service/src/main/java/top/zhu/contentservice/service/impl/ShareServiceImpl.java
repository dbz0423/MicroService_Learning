package top.zhu.contentservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.zhu.contentservice.mapper.ShareMapper;
import top.zhu.contentservice.model.entity.Share;
import top.zhu.contentservice.service.ShareService;

@Service
@AllArgsConstructor
public class ShareServiceImpl extends ServiceImpl<ShareMapper , Share> implements ShareService {
    @Override
    public Share getShare(Integer id) {
        Share share = baseMapper.selectById(id);
        return share;
    }
}
