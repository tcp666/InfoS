package com.keshe.tools;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateChange
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/29 22:36
 * @Version 1.0
 */
@Component
public class DateChange {
    /**
     * 把标准格式的字符串转换成Date类型
     * yyyy-MM-dd
     * @param date
     * @return
     * @throws ParseException
     */
    public Date strTodate(String date)throws ParseException {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(date);
    }


}
