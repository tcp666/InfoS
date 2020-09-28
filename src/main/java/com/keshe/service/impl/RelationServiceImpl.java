package com.keshe.service.impl;

import com.keshe.entity.Relation;
import com.keshe.entity.RetJsonData;
import com.keshe.entity.User;
import com.keshe.mapper.RelationMapper;
import com.keshe.mapper.UserMapper;
import com.keshe.service.RelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RelationServiceImpl
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:50
 * @Version 1.0
 */

@Service
public class RelationServiceImpl implements RelationService {
    @Resource
    private RelationMapper relationMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public RetJsonData addRelation(Relation relation) {
        if (relationMapper.verifyRelation(relation.getUserId(), relation.getUserById()) == null) {
            if (relationMapper.addRelation(relation) > 0 && userMapper.icrUserFans(relation.getUserById()) > 0) {
                return new RetJsonData(true, "关注成功", null);
            } else {
                return new RetJsonData(false, "关注失败");
            }
        }else{
            return new RetJsonData(false, "已被关注");
        }
    }

    @Override
    public RetJsonData delRelation(String userId, String userById) {
        if (relationMapper.delRelation(userId, userById) > 0 && userMapper.redUserFans(userById) > 0){
            return new RetJsonData(true,"取关成功", null);
        }else{
            return new RetJsonData(false,"取关失败");
        }
    }


    @Override
    public RetJsonData relationInfoPage(String userId) {
        List<User> users = relationMapper.followUserByUserId(userId);
        Integer followCount = users.size();
        User user = userMapper.userByUserId(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("followCount", followCount);
        map.put("user", user);
        map.put("users",users);
        if (user != null){
            return new RetJsonData(true,map,null);
        }else{
            return new RetJsonData(false,"查询失败");
        }
    }
}
