package top.zhu.contentservice.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_bonus_log")
public class BonusLog {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String event;

    private Integer bonus;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
