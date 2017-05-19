package com.rush.chat.tools;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by cfc
 * 2017/2/8.
 */
public class VaildUtils {

    private static Logger logger = LoggerFactory.getLogger(VaildUtils.class);
    /**
     * 校验bean
     *
     * @param object
     * @param vaildType
     * @return
     */
    public static String  vaildBeanErr(Object object, Class vaildType)throws Exception{
        StringBuffer errorMsg = new StringBuffer("");
        try {
            Object passportlends = object;
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Object>> constraintViolations=null;
            if(vaildType==null){
                constraintViolations = validator
                        .validate(passportlends);
            }else{
                constraintViolations = validator
                        .validate(passportlends, vaildType);
            }
            for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
                errorMsg.append(constraintViolation.getPropertyPath().toString()).append(constraintViolation.getMessage());
            }
        } catch (Exception e) {
            logger.error("验证" + object.getClass() + "异常", e);
            throw e;
        }
        return errorMsg.toString();
    }


    public static boolean isImg(String ext){
        if ( "png".equals(ext) || "jpg".equals(ext) || "gif".equals(ext) ||"jpeg".equals(ext) ){
            return true;
        }
        return false;
    }

}
