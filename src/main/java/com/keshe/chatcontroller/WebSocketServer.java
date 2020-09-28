package com.keshe.chatcontroller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keshe.entity.ChatRecord;
import com.keshe.service.impl.ChatRecordServiceimpl;
import com.keshe.tools.ApplicationContextRegister;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;

/**
 * websocketServer：Websocket服务
 * author:tcp
 */
@ServerEndpoint(value = "/websocket/{uid}")
@Component
public class WebSocketServer {
    private static int onlineCount = 0;
    private static HashMap<String, Session> userSession = new HashMap();
    private Session session;
    private String uid = "";

    public WebSocketServer() {
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid) {
        System.out.println(uid);
        this.session = session;
        userSession.put(uid, session);
        addOnlineCount();
        this.uid = uid;
//        try {
////            this.sendMessage("连接成功");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @OnClose
    public synchronized void onClose() {
//        System.out.println("close..." + this.uid);
        userSession.remove(this.uid);
        subOnlineCount();
//        System.out.println("上线人数减一");
    }

    @OnMessage
    public synchronized void onMessage(String message, Session session) {
        try {
            ApplicationContext act = ApplicationContextRegister.getApplicationContext();

            ChatRecordServiceimpl chatRecordServiceimpl = act.getBean(ChatRecordServiceimpl.class);
            System.out.println(message);
            ObjectMapper objectMapper = new ObjectMapper();
            ChatRecord chatRecord = objectMapper.readValue(message, ChatRecord.class);
//            System.out.println(chatRecord.toString());
//            System.out.println("chatRecordServiceimpl==null:"+chatRecordServiceimpl==null);
//            System.out.println(chatRecord);
//            System.out.println(chatRecordServiceimpl);
            int x = chatRecordServiceimpl.save(chatRecord);
            Session session1 = userSession.get(chatRecord.getToUserId());
            if (session1 != null)
                session1.getBasicRemote().sendText(message);
        } catch (JsonParseException var6) {
            var6.printStackTrace();
        } catch (JsonMappingException var7) {
            var7.printStackTrace();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        ++onlineCount;
    }

    public static synchronized void subOnlineCount() {
        --onlineCount;
    }
}
