package com.keshe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName Role
 * @Description TODO  角色表
 * @Author Haining
 * @Date 2020/5/27 9:02
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Role {
    private String roleId;
    private String roleName;
    private String roleDes;
    private String rolePower;
}
