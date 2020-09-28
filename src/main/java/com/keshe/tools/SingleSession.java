package com.keshe.tools;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @ClassName SingleSession
 * @Description TODO
 * @Author Haining
 * @Date 2020/5/28 12:40
 * @Version 1.0
 */
public class SingleSession{
    // SessionFactory工厂 用于获取Session
    private static HttpSession session;

    // 构造方法私有化
    private SingleSession() {}

    // 初始化SessionFactory
    private static void init() {
        // 不为空则创建SessionFactory
        if (session == null) {
            session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        }
    }

    /** 获取连接 */
    public static HttpSession getSession() {
        if (session == null) {
            init();
        }
        return session;
    }
}
