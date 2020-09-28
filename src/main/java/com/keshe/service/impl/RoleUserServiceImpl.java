package com.keshe.service.impl;

import com.keshe.mapper.RoleUserMapper;
import com.keshe.service.RoleUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName RoleUserServiceImpl
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 10:04
 * @Version 1.0
 */

@Service
public class RoleUserServiceImpl implements RoleUserService {
    @Resource
    private RoleUserMapper roleUserMapper;
}
