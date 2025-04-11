package top.zhu.userservice.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVo {

    private Integer id;

    private String username;

    private String mobile;

    private String avatarUrl;

    private Integer bonus;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

}
