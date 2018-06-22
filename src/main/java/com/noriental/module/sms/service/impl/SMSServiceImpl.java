package com.noriental.module.sms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.noriental.module.sms.dao.SmsSendMapper;
import com.noriental.module.sms.model.SmsSend;
import com.noriental.module.sms.service.SMSService;
import com.noriental.module.user.model.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SMSServiceImpl implements SMSService {

    @Autowired
    SmsSendMapper mapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(SmsSend record) {
        return 0;
    }

    @Override
    public int insertSelective(SmsSend record) {
        return 0;
    }

    @Override
    public SmsSend selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(SmsSend record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SmsSend record) {
        return 0;
    }

    @Override
    public PageInfo<UserDomain> selectAll(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        //PageHelper.startPage(1, 10);
        List<UserDomain> userDomains = mapper.selectAll();
        PageInfo result = new PageInfo(userDomains);

        return result;

    }
}
