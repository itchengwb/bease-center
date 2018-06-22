package com.noriental.module.sms.service;

import com.github.pagehelper.PageInfo;
import com.noriental.module.sms.model.SmsSend;
import com.noriental.module.user.model.UserDomain;

public interface SMSService {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsSend record);

    int insertSelective(SmsSend record);

    SmsSend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsSend record);

    int updateByPrimaryKey(SmsSend record);


    PageInfo<UserDomain> selectAll(int pageNum, int pageSize);

}
