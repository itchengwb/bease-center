package com.noriental.module.user.controller;

import com.noriental.module.user.model.UserDomain;
import com.noriental.module.user.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;

/**
 * Created by Wenbo Cheng on2017/8/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(UserDomain user){
        return userService.addUser(user);
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        return userService.findAllUser(pageNum,pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object find(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        LOGGER.info("=========findAllUser=======");

        return userService.findAllUser(pageNum,pageSize);

    }
}
