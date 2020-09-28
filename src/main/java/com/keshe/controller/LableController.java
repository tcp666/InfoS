package com.keshe.controller;

import com.keshe.entity.RetJsonData;
import com.keshe.service.LableService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/5/27 10:00
 */
@RestController
@RequestMapping("/lable")
public class LableController {

    @Resource
    private LableService lableService;

    /**
     * 返回标签
     *  成功：返回   success:true
     *               errorMsg:NULL
     *               data:List<lable>
     *  失败：返回   success:false
     *               errorMsg:查询失败/该动态不存在
     *               data:null
     * @return
     */
    @RequestMapping("/getAllLable")
    @ResponseBody
    public RetJsonData getAllLable(){
        return lableService.getAllLable();
    }

}
