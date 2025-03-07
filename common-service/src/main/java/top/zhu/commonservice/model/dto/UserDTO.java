package top.zhu.commonservice.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Integer id;

    private String username;

    private String mobile;

    private String avatarUrl;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

}
