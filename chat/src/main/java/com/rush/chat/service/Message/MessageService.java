package com.rush.chat.service.Message;

import com.rush.chat.dao.mapper.TMessageMapper;
import com.rush.chat.models.TMessage;
import com.rush.chat.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by cfc
 * 2017/3/17.
 */
@Service
public class MessageService extends BaseServiceImpl implements IMessageService {

    private TMessageMapper messageMapper ;

    @Autowired
    public void setMessageMapper(TMessageMapper messageMapper) {
        this.messageMapper = messageMapper;
        super.baseMapper = messageMapper;
    }

    @Override
    public Integer addBasic(Object obj) {
        int num = messageMapper.insertSelective((TMessage)obj);
        return num;
    }

    @Override
    public Integer modifyBasic(Object obj) {
        int num = messageMapper.updateByPrimaryKeySelective((TMessage)obj);
        return num;
    }

    @Override
    public Integer delBasic(Object obj) {
        return null;
    }

}
