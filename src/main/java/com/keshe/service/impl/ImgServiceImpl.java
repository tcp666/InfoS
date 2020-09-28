package com.keshe.service.impl;

import com.keshe.mapper.ImgMapper;
import com.keshe.service.ImgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName ImgServiceImpl
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/27 9:47
 * @Version 1.0
 */

@Service
public class ImgServiceImpl implements ImgService {
    @Resource
    private ImgMapper imgMapper;
}
