package com.jiawa.wiki.service;

import com.jiawa.wiki.domain.Doc;
import com.jiawa.wiki.websocket.WebSocketServer;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WsService {
    @Resource
    public WebSocketServer webSocketServer;

    // 调用这个函数时候， 调用方和当前函数不能在同一个类中
    @Async
    public void sendInfo(String message, String logId) {
        MDC.put("LOG_ID", logId);
        webSocketServer.sendInfo(message);
    }
}


