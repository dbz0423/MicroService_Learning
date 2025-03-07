package top.zhu.userservice.model.vo;

import lombok.Data;

@Data
public class Result {
    private int code;
    private String msg;
    private Object data;
}
