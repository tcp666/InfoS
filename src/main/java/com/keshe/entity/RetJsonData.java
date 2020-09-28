package com.keshe.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName RetJsonData
 * @Description TODO  包装类
 * @Author Haining
 * @Date 2020/5/28 9:09
 * @Version 1.0
 */

@ToString
@Data
public class RetJsonData {
    //异常状态判断
    private boolean success;
    //返回数据
    private Object data;
    //输出页面提示
    private String errorMsg;
    //成功的时候
    public RetJsonData(boolean success){
        this.success = success;
    }

    public RetJsonData(boolean success, Object date, String errorMsg){
        this.success = success;
        this.data = date;
        this.errorMsg = errorMsg;
    }
    public RetJsonData(boolean success, String errorMsg){
        this.success = success;
        this.errorMsg = errorMsg;
    }


}
