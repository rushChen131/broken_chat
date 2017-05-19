package com.rush.chat.websocket;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rush.chat.constant.CommunicationConstant;
import com.rush.chat.tools.CustNoUtils;
import com.rush.chat.tools.GlobalUtils;
import com.rush.chat.tools.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;


/**
* Socket处理器
*
* @author cfc
*/
@Component
public class MyWebSocketHandler implements WebSocketHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final Map<String, WebSocketSession> userSocketSessionMap;//未匹配的用户
    public static final Map<String, WebSocketSession> matchedUserSocketSessionMap;//已匹配的用户

    public static final Map<String ,String >  matchingMap; //

	static {
		userSocketSessionMap = new HashMap<String, WebSocketSession>();
        matchedUserSocketSessionMap = new HashMap<String, WebSocketSession>();
        matchingMap = new HashMap<String, String>();
	}

	/**
	 * 建立连接后
	 */
	public void afterConnectionEstablished(WebSocketSession session)
			 {
//		String authToken = (String) session.getAttributes().get("authToken");
//        String registerType = (String)session.getAttributes().get("registerType");
//        if (RegisterTypeConstant.CUST.getCode().equals(registerType)){
//            /**
//             *  1、查询坐席
//             *  2、通知客户端对应的坐席
//             *  3、通知坐席那个客户连接
//             */
//                Seat seat = GetSeatApi.getSeat();
//                logger.info("客户登录========"+authToken+"对应坐席========"+seat.getStaffNo());
//                String custName = CustNoUtils.getCustName();
//                matchingMap.put(authToken,seat.getStaffNo());//用户跟坐席关联
//                TMessage custMessage = new TMessage();//2、通知客户
//                custMessage.setNotifyOnLine();
//                custMessage.setFromUserMsg(seat.getStaffNo(), seat.getNikeName());
//                custMessage.setToUserMsg(authToken, custName);
//                custMessage.setMessageValue(GlobalUtils.getString("welcome_message"));//提示语
//                custMessage.setSeatStatus(SeatStatusConstant.ON_LINE.getCode());
//                sendMessageToUser(session, JsonUtils.toJson(custMessage));
//
//                TMessage seatMessage = new TMessage(); //3、通知坐席
//                seatMessage.setNotifyOnLine();
//                seatMessage.setFromUserMsg(authToken, custName);
//                seatMessage.setToUserMsg(seat.getStaffNo(), seat.getNikeName());
//                seatMessage.setMessageValue(GlobalUtils.getString("welcome_message"));//提示语
//                sendMessageToUser(getSeatSession(seat.getStaffNo()),JsonUtils.toJson(seatMessage));
//        }else {
//            WebSocketSession socketSession = null;
//            if (RegisterTypeConstant.SEAT.getCode().equals(registerType)) {
//                socketSession = seatSocketSessionMap.get(authToken);
//            }
//            if (socketSession != null && socketSession.isOpen()) {//如果原先在的踢出 保持唯一
//                try {
//                    socketSession.close();
//                } catch (IOException e) {
//                    logger.error("关闭socket异常", e);
//                }
//            }
////          //非客户验证合法性
//            Map<String, Object> hashMap = new HashMap<String, Object>();
//            hashMap.put("isRead", MessageIsReadConstant.False.getCode());
//            hashMap.put("toUser", authToken);
//        }
//        Map<String, WebSocketSession> sessionMap = getSessionMap(registerType);//获得对应的sessionMap
//        if (sessionMap.get(authToken) ==null){
//            sessionMap.put(authToken, session);
//        }
	}

    private WebSocketSession getSeatSession(String seatToken){
        WebSocketSession webSocketSession = null;
        if (seatSocketSessionMap.get(seatToken) != null) {
            webSocketSession  =seatSocketSessionMap.get(seatToken);
        }
        return webSocketSession;
    }

    /**
	 * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
	 */
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)  {
//        try {
//            if(message.getPayloadLength()==0){
//                logger.info("message.getPayloadLength()====="+message.getPayloadLength());
//                return;
//            }
//            ObjectMapper objectMapper = new ObjectMapper();
//            TMessage msg = null;
//            try {
//                msg = objectMapper.readValue(message.getPayload().toString(), TMessage.class);
//            } catch (Exception e) {
//                logger.info("转换异常 ==========  message.getPayload().toString()=========="+message.getPayload().toString(),e);
//                return;
//            }
//            sendMessageToUser(getSession(msg.getToUser(),msg.getRegisterType()),JsonUtils.toJson(msg));
//        } catch (Exception e) {
//           logger.error("消息处理异常",e);
//        }

    }
//
//    /**
//     *
//     * @param token
//     * @return
//     */
//    private WebSocketSession getSession(String token,String registerType){
////            if (RegisterTypeConstant.CUST.getCode().equals(registerType)){
////                return getSeatSession(token);
////            }else {
////                return userSocketSessionMap.get(token);
////            }
//    }


	/**
	 * 消息传输错误处理
	 */
	public void handleTransportError(WebSocketSession session,
			Throwable exception)  {
        logger.error("通讯异常=================", exception);
        removeSession(session);
        if (session.isOpen()) {
            try {
                session.close();
            } catch (IOException e) {
                logger.error("会话关闭异常",e);
            }
        }
    }
//
//    /**
//     * 获得对应session存放的map
//     * @param registerType
//     * @return
//     */
//    public Map<String, WebSocketSession> getSessionMap(String registerType){
//        if (RegisterTypeConstant.CUST.getCode().equals(registerType)){
//              return   userSocketSessionMap;
//        }else if (RegisterTypeConstant.SEAT.getCode().equals(registerType)){
//                return seatSocketSessionMap;
//        }
//        return null;
//    }


    /**
     * 移除连接处理
     * @param session
     */
    private void removeSession(WebSocketSession session){
//        String registerType = (String)session.getAttributes().get("registerType");
//        String authToken = (String) session.getAttributes().get("authToken");
//        String channel = (String )session.getAttributes().get("channel");
//        DispathData dispathData = new DispathData();
//        dispathData.setMerchantNo(channel);
//        if (RegisterTypeConstant.CUST.getCode().equals(registerType)){
//            dispathData.setStaffNo(matchingMap.get(authToken));
//        }else {
//            dispathData.setStaffNo(authToken);
//        }
//        dispathData.setChangeType(registerType);
//         //解除客户和坐席的关系
//        if (RegisterTypeConstant.CUST.getCode().equals(registerType)){
//            matchingMap.remove(authToken);
//        }else{
//            Iterator<Entry<String, String >> it = matchingMap
//                    .entrySet().iterator();
//            while (it.hasNext()) {
//                Entry<String, String > entry = it.next();
//                if (entry.getValue().equals(authToken)){
//                    it.remove();
//                    matchingMap.remove(entry.getKey());
//                }
//            }
//        }
//        Map<String, WebSocketSession> sessionMap = getSessionMap(registerType);
//        Iterator<Entry<String, WebSocketSession>> it = sessionMap
//                .entrySet().iterator();
//        // 移除Socket会话
//        while (it.hasNext()) {
//            Entry<String, WebSocketSession> entry = it.next();
//            if (entry.getValue().getId().equals(session.getId())) {
//                sessionMap.remove(entry.getKey());
//               logger.info("Socket会话已经移除:用户ID" + entry.getKey());
//                break;
//            }
//        }
    }

	/**
	 * 关闭连接后
     * 客户端:通知坐席
	 */
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) {
//        String registerType =(String)session.getAttributes().get("registerType");
//        String authToken = (String) session.getAttributes().get("authToken");
//        String channel = (String)session.getAttributes().get("channel");
//        logger.info("authToken ======"+authToken+"=======断开了链接");
//        if (RegisterTypeConstant.CUST.getCode().equals(registerType)){
//            String seatToken = matchingMap.get(authToken);
//            if (seatToken != null && !"".equals(seatToken) && getSeatSession(seatToken) != null){
//                TMessage msg = new TMessage();
//                msg.setMessageType(CommunicationConstant.NOTIFY.getCode());
//                msg.setNotifyStatus(NotityStatusConstant.OFF_LINE.getCode());
//                msg.setFromUser(authToken);
//                msg.setToUser(seatToken);
//                msg.setMessageValue(GlobalUtils.getString("welcome_message"));//提示语
//                sendMessageToUser(getSeatSession(seatToken),JsonUtils.toJson(msg));
//            }
//        }else {
//            Iterator<Entry<String, String >> it = matchingMap
//                    .entrySet().iterator();
//            while (it.hasNext()) {
//                Entry<String, String > entry = it.next();
//                if (entry.getValue().equals(authToken)){
//                    WebSocketSession socketSession = getSession(authToken,registerType);
//                    TMessage message = new TMessage();
//                    message.setFromUser(authToken);
//                    message.setToUser(entry.getKey());
//                    message.setMessageType(CommunicationConstant.NOTIFY.getCode());
//                    message.setNotifyStatus(NotityStatusConstant.OFF_LINE.getCode());
//                    sendMessageToUser(socketSession, JsonUtils.toJson(message));
//                    it.remove();
//                    matchingMap.remove(entry.getKey());
//                }
//            }
//
//        }
//        removeSession(session);
	}

	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给某个用户发送消息
	 * @param message
	 * @throws IOException
	 */
	public void sendMessageToUser(WebSocketSession session, TextMessage message)
			{
        if (session != null && session.isOpen()) {
            logger.info("发送者=============" + session.getAttributes().get("authToken") + "send=================" + message.getPayload().toString());
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                logger.error("发送消息异常",e);
                handleTransportError(session, e);
            }
        }
	}



}
