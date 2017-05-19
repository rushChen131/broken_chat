package com.rush.chat.models;

import java.io.Serializable;

/**
 * Created by cfc
 * 2017/3/19.
 */
public class UpLoadResult implements Serializable {

    private String nickName;

    private String url;

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
