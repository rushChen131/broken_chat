package com.rush.chat.constant;

public enum MessageConstant {
    SUCCESS("0000", "操作成功"),
    FAILURE("1111","操作失败"),
    ERR_PARAMETER("2222","参数错误"),
    SYS_ERROR("9999","系统异常"),

    FILE_TYPE_ERROR("2001","文件格式错误"),
    NO_FILE("2002","文件不能为空");

    private String code;
    private String desc;

    MessageConstant(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

}
