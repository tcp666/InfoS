package com.keshe.controller;

import com.keshe.service.RoleService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/5/27 10:07
 */
@RestController
public class RoleController {

    @Resource
    private RoleService roleService;
}
