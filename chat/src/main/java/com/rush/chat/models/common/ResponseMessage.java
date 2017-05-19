package com.rush.chat.models.common;

import com.rush.chat.constant.MessageConstant;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.Serializable;

public class ResponseMessage implements Serializable {
	private String rspCode;
	private String rspMsg;
	private String sign;
	private Object data;

	public ResponseMessage(String rspCode, String rspMsg, Object data) {
		// super();
		this.rspCode = rspCode;
		this.rspMsg = rspMsg;
		this.data = data;
	}

	public static ResponseMessage success(Object data) {
		return new ResponseMessage(MessageConstant.SUCCESS.getCode(), MessageConstant.SUCCESS.getDesc(), data);
	}

	public static ResponseMessage success() {
		return success("");
	}

    public static ResponseMessage failure() {
        return new ResponseMessage(MessageConstant.FAILURE.getCode(), MessageConstant.FAILURE.getDesc(), null);
    }


    public static ResponseMessage error() {
        return error(MessageConstant.FAILURE.getCode(), MessageConstant.FAILURE.getDesc());
    }

    public static ResponseMessage error(String message) {
        return error(MessageConstant.FAILURE.getCode(), message);
    }

	public static ResponseMessage error(String code, String message) {
        return new ResponseMessage(code, message, null);
    }
    public static ResponseMessage error(String code, String message,Object data) {
        return new ResponseMessage(code, message, data);
    }

    public static ResponseMessage paramErr(){
        return new ResponseMessage(MessageConstant.ERR_PARAMETER.getCode(),MessageConstant.ERR_PARAMETER.getDesc(),"");
    }

    public static ResponseMessage paramErr(String rspMsg){
        return new ResponseMessage(MessageConstant.ERR_PARAMETER.getCode(),rspMsg,"");
    }

    public static ResponseMessage paramErr(Object data){
        return new ResponseMessage(MessageConstant.ERR_PARAMETER.getCode(),MessageConstant.ERR_PARAMETER.getDesc(),data);
    }
	

	public String getRspCode() {
		return rspCode;
	}

	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}

	public String getRspMsg() {
		return rspMsg;
	}

	public void setRspMsg(String rspMsg) {
		this.rspMsg = rspMsg;
	}


	public String getSign() throws JsonProcessingException {
		// 签名为空并且数据data不为空 做签名
		/*if (sign == null) {
			// 处理加密数据
			ObjectMapper mapper = new NullJsonObjectMaper();
			String dataSrc = mapper.writeValueAsString(this.getData());
			StringBuffer srcSign = new StringBuffer();
			srcSign.append("sendTime=").append(this.getSendTime())
					.append("data=").append(dataSrc);
			sign = Md5.md5Upper(srcSign.toString());
			this.data = dataSrc;
		}*/
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Object getData() throws JsonProcessingException {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "rspCode='" + rspCode + '\'' +
                ", rspMsg='" + rspMsg + '\'' +
                ", sign='" + sign + '\'' +
                ", data=" + data +
                '}';
    }
}
