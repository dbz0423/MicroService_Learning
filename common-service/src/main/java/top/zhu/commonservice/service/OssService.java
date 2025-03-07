package top.zhu.commonservice.service;

import org.springframework.web.multipart.MultipartFile;
import top.zhu.commonservice.model.dto.FileDTO;

public interface OssService {

    FileDTO upload(MultipartFile uploadFile);
}
