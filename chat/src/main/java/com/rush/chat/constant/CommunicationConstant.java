package com.rush.chat.constant;

/**
 * Created by cfc
 * on 2017/3/15.
 */
public enum CommunicationConstant {
    NOTIFY("01","通知"),
    TEXT("02","文字"),
    IMAGE("03","图片"),
    FILE("04","文件"),
    HEARTBEAT("05","心跳"),
    VOICE("07","语音");

    private String code;
    private String desc;

    CommunicationConstant(String code, String desc) {
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
