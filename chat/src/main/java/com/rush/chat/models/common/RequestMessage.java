package com.rush.chat.models.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rush.chat.constant.MessageConstant;
import com.rush.chat.exception.ParamsVaildErrException;
import com.rush.chat.exception.WebInfoException;
import com.rush.chat.tools.DataUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("serial")
public class RequestMessage<T> implements Serializable {
	private String source;
	private String data;
	private String token;
	private String version;
	private DataMap<String, Object> dataMap;

	private String sign;

	public String getData() {
		return data;
	}

	public void setData(String data) throws WebInfoException {
		this.data = data;
		ObjectMapper objMapper = new ObjectMapper();
		try {
			dataMap = objMapper.readValue(data, DataMap.class);
		} catch (Exception e) {
			throw new WebInfoException(MessageConstant.ERR_PARAMETER.getCode(),
					"数据格式错误");
		}
	}

	public DataMap<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(DataMap<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

    /**
     * 校验bean
     * @param objClass  实体类
     * @param vaildType 分组
     */
    public void vaildBean(Class objClass, Class vaildType) {
        Map<String, String> errorMap = vaildBeanErr(objClass, vaildType);
        if (errorMap != null && errorMap.size() > 0) {
            throw new ParamsVaildErrException(MessageConstant.ERR_PARAMETER.getCode(), MessageConstant.ERR_PARAMETER.getDesc(), errorMap);
        }else if(errorMap==null){
            throw new ParamsVaildErrException(MessageConstant.FAILURE.getCode(), MessageConstant.FAILURE.getDesc());
        }
    }



    /**
     * 校验bean
     *
     * @param object
     * @param vaildType
     * @return
     */
    private  Map<String, String>  vaildBeanErr(Class object, Class vaildType){
        Map<String, String> errorMap = new HashMap<String ,String >();
        try {
            T passportlends = (T) DataUtils.convertMap(object, dataMap);
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<T>> constraintViolations=null;
            if(vaildType==null){
                constraintViolations = validator
                        .validate(passportlends);
            }else{
                constraintViolations = validator
                        .validate(passportlends, vaildType);
            }
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                errorMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
            }
        } catch (Exception e) {
           errorMap = null;
        }
        return errorMap;
    }


}
