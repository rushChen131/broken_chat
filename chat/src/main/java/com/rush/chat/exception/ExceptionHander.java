package com.rush.chat.exception;

import com.rush.chat.models.common.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.BindException;

/**
 * controller异常处理 公用类
 */
@ControllerAdvice(annotations = Controller.class)

public class ExceptionHander {
	private Logger logger = LoggerFactory.getLogger(this.getClass());


    @ResponseBody
    @ExceptionHandler(ParamsVaildErrException.class)
    public ResponseMessage ParamsVaildErrExceptionHandle(ParamsVaildErrException ex) {
        logger.error(ex.getExceptionMesssage(), ex);
        return ResponseMessage.paramErr(ex.getErrorMap());
    }

    @ResponseBody
    @ExceptionHandler(WebInfoException.class)
    public ResponseMessage webInfoExcpetionHandle(WebInfoException ex) {
        logger.error(ex.getMessage(), ex);
        return ResponseMessage.error(ex.getExceptionCode(),
                ex.getExceptionMesssage());
    }

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ResponseMessage serviceExceptionHandle(ServiceException ex) {
        logger.error(ex.getMessage(), ex);
        if (StringUtils.isEmpty(ex.getExceptionCode())) {
            return ResponseMessage.error();
        } else {
            return ResponseMessage.error(ex.getExceptionCode(),
                    ex.getExceptionMesssage(),ex.getErrorMap());
        }
    }

	@ResponseBody
	@ExceptionHandler(BindException.class)
	public ResponseMessage BindException(Exception ex) {
		logger.error(ex.getMessage(), ex);
		return ResponseMessage.error();
	}

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseMessage systemException(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return ResponseMessage.error();
    }

}
