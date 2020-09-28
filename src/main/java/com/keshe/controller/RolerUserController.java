package com.keshe.controller;

import com.keshe.service.RoleUserService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/5/27 10:08
 */
@RestController
public class RolerUserController {

    @Resource
    private RoleUserService roleUserService;
}
