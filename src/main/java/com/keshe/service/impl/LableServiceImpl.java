package com.keshe.service.impl;

import com.keshe.entity.Lable;
import com.keshe.entity.RetJsonData;
import com.keshe.mapper.LableMapper;
import com.keshe.service.LableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName LableServiceImpl
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:48
 * @Version 1.0
 */

@Service
public class LableServiceImpl implements LableService {
    @Resource
    private LableMapper lableMapper;

    @Override
    public RetJsonData getAllLable() {
        List<Lable> lables = lableMapper.getAllLable();
        if (lables.size() != 0){
            return new RetJsonData(true, lables, null);
        }
        return new RetJsonData(false, "查询失败");
    }

}
