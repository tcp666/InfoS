package com.keshe.service.impl;

import com.keshe.mapper.RoleMapper;
import com.keshe.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName RoleSrviceImpl
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:55
 * @Version 1.0
 */

@Service
public class RoleSrviceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
}
