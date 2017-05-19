package com.rush.chat.tools;

import com.google.gson.GsonBuilder;
import com.rush.chat.models.TMessage;
import org.springframework.web.socket.TextMessage;

/**
 * Created by cfc
 * 2017/3/28.
 */
public class JsonUtils {

    public static TextMessage toJson(TMessage message){
        return new TextMessage(new GsonBuilder().create().toJson(message));
    }
}
