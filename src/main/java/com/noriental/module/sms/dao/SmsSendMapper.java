package com.noriental.module.sms.dao;

import com.noriental.module.sms.model.SmsSend;
import com.noriental.module.user.model.UserDomain;

import java.util.List;

public interface SmsSendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsSend record);

    int insertSelective(SmsSend record);

    SmsSend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsSend record);

    int updateByPrimaryKey(SmsSend record);


    List<UserDomain> selectAll();
}