
package com.rush.chat.websocket;

import java.util.Map;


import com.rush.chat.tools.CustNoUtils;
import com.rush.chat.tools.DataUtils;
import com.rush.chat.tools.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;


/**
 * Socket建立连接
 * @author cfc
 */
public class HandShake implements HandshakeInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 建立连接之前
     * @param request
     * @param response
     * @param wsHandler
     * @param attributes
     * @return
     * @throws Exception
     */
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
            String parms = request.getURI().getQuery();
            logger.info("登录 url=========="+parms);
            Map<String,String > map = DataUtils.getParams(parms);
			// 标记用户
			if(!DataUtils.isEmpty(map.get("authToken"))&&DataUtils.isEmpty(map.get("nickName"))){
				attributes.put("authToken", map.get("authToken"));
				attributes.put("nickName", map.get("nickName"));
			}else{
				return false;
			}
		}
		return true;
	}



	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
	}

}
