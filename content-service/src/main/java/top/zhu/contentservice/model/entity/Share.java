package top.zhu.contentservice.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_share")
public class Share {

    @TableId
    private Integer id;

    @TableField("user_id")
    private Integer userId;

    private String title;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "is_original")
    private Boolean isOriginal;

    private String author;

    private String cover;

    private String summary;

    private Integer price;

    @TableField(value = "download_url")
    private String downloadUrl;

    @TableField(value = "buy_count")
    private Integer buyCount;

    @TableField(value = "show_flag")
    private Boolean showFlag;

    @TableField(value = "audit_status")
    private String auditStatus;

    private String reason;
}
