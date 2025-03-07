package top.zhu.commonservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.zhu.commonservice.common.result.Result;
import top.zhu.commonservice.model.dto.FileDTO;
import top.zhu.commonservice.service.OssService;

@RestController("/oss")
@AllArgsConstructor
public class OssTestController {

    private final OssService ossService;

    @PostMapping(value = "/upload/img")
    @ResponseBody
    public Result<FileDTO> upload(@RequestBody MultipartFile file) {
        return Result.ok(ossService.upload(file));
    }
}
