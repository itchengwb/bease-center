package com.noriental.module.user.service.user;

import com.github.pagehelper.PageInfo;
import com.noriental.module.user.model.UserDomain;

/**
 * Created by Wenbo Cheng on2018/4/19.
 */
public interface UserService {

    int addUser(UserDomain user);

    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);
}
