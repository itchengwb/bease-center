package com.noriental.module.user.dao;


import com.noriental.module.user.model.UserDomain;

import java.util.List;

public interface UserDao {


    int insert(UserDomain record);



    List<UserDomain> selectUsers();
}