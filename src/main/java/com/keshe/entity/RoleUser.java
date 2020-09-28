package com.keshe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName RoleUser
 * @Description TODO   用户角色中间表
 * @Author Haining
 * @Date 2020/5/27 9:04
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class RoleUser {
    private String roleId;
    private String userId;
}
